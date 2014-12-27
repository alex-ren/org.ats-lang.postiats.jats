
staload "./conats.sats"
staload UN = "prelude/SATS/unsafe.sats"

(* ************* ************* *)

// Define linear buffer to prevent resource leak.
absviewtype lin_buffer (a:t@ype)

fun lin_buffer_create {a:t@ype} (
  data: a): lin_buffer a = let
  val ref = conats_atomref_create (data)
  val lref = $UN.castvwtp0 {lin_buffer a} (ref)
in
  lref
end

fun lin_buffer_update {a:t@ype} (
  lref: lin_buffer a, data: a): lin_buffer a = let
  val ref = $UN.castvwtp0 {atomref a} (lref)
  val () = conats_atomref_update (ref, data)
  val lref = $UN.castvwtp0 (ref)
in
  lref
end

fun lin_buffer_get {a:t@ype} (
  lref: lin_buffer a): (lin_buffer a, a) = let
  val ref = $UN.castvwtp0 {atomref a} (lref)
  val v = conats_atomref_get ref
  val lref = $UN.castvwtp0 (ref)
in
  (lref, v)
end

(* ************* ************* *)

// Define linear integer buffer for demonstration.
viewtypedef demo_buffer = lin_buffer int

fun demo_buffer_isful (buf: demo_buffer): (demo_buffer, bool) = let
  val (buf, len) = lin_buffer_get (buf)
in
  (buf, len > 2)  // Assume the buffer can only hold 2 elements.
end

fun demo_buffer_isnil (buf: demo_buffer): (demo_buffer, bool) = let
  val (buf, len) = lin_buffer_get (buf)
in
  (buf, len <= 0)
end

fun demo_buffer_insert (buf: demo_buffer): demo_buffer = let
  val (buf, len) = lin_buffer_get (buf)
  val buf = lin_buffer_update (buf, len + 1)
in 
  buf
end

fun demo_buffer_takeout (buf: demo_buffer): demo_buffer = let
  val (buf, len) = lin_buffer_get (buf)
  val buf = lin_buffer_update (buf, len - 1)
in 
  buf
end

(* ************* ************* *)

// Create a buffer for model construction.
val db: demo_buffer = lin_buffer_create (0)

// Turn a linear buffer into a shared buffer.
val s = conats_shared_create {demo_buffer}(db)

// Keep adding elements into buffer.
fun producer (x: int):<fun1> void = let
  val db = conats_shared_acquire (s)

  fun insert (db: demo_buffer):<cloref1> demo_buffer = let
    val (db, isful) = demo_buffer_isful (db)
  in
    if isful then let
      val db = conats_shared_condwait (s, db)
    in
      insert (db)
    end else let 
      val (db, isnil) = demo_buffer_isnil (db)
      val db = demo_buffer_insert (db)
    in
      if isnil then let
        val db = conats_shared_signal (s, db)
      in db end
      else db
    end
  end
  
  val db = insert (db)
  val () = conats_shared_release (s, db); 
in
  producer (x)
end

// Keep removing elements into buffer.
fun consumer (x: int):<fun1> void = let
  val db = conats_shared_acquire (s)

  fun takeout (db: demo_buffer):<cloref1> demo_buffer = let
    val (db, isnil) = demo_buffer_isnil (db)
  in
    if isnil then let
      val db = conats_shared_condwait (s, db)
    in
      takeout (db)
    end else let
      val (db, isful) = demo_buffer_isful (db)
      val db = demo_buffer_takeout (db)
    in
      if isful then let
        // val db = conats_shared_signal (s, db)
      in db end
      else db
    end
  end

  val db = takeout (db)
  val () = conats_shared_release (s, db); 
in
  consumer (x)
end

// Construct the model of whole system.

val tid1 = conats_tid_allocate ()
val tid2 = conats_tid_allocate ()

val () = conats_thread_create(producer, 0, tid1)
val () = conats_thread_create(consumer, 0, tid2)

// List the properties for model checking.

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}





// One producer and two consumer, two condition, no deadlock.

#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"

(* ************* ************* *)

// Define linear buffer to prevent resource leak.
absviewtype lin_buffer (a:t@ype)

local
  assume lin_buffer (a) = atomref (a)
in
  fun lin_buffer_create {a:t@ype} (
    data: a): lin_buffer a = let
    val ref = conats_atomref_create (data)
  in
    ref
  end
  
  fun lin_buffer_update {a:t@ype} (
    lref: lin_buffer a, data: a): lin_buffer a = let
    val () = conats_atomref_update (lref, data)
  in
    lref
  end
  
  fun lin_buffer_get {a:t@ype} (
    lref: lin_buffer a): (lin_buffer a, a) = let
    val v = conats_atomref_get lref
  in
    (lref, v)
  end
end


(* ************* ************* *)

// Define linear integer buffer for demonstration.
viewtypedef demo_buffer = lin_buffer int

fun demo_buffer_isful (buf: demo_buffer): (demo_buffer, bool) = let
  val (buf, len) = lin_buffer_get (buf)
in
  (buf, len > 0)  // Assume the buffer can only hold 1 elements.
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
val s = conats_sharedn_create {demo_buffer}(db, 2)

#define NOTEMP 0
#define NOTFUL 1

// Keep adding elements into buffer.
fun producer (x: int):<fun1> void = let
  val db = conats_shared_acquire (s)

  fun insert (db: demo_buffer):<cloref1> demo_buffer = let
    val (db, isful) = demo_buffer_isful (db)
  in
    if isful then let
      val db = conats_sharedn_condwait (s, NOTEMP, db)
    in
      insert (db)
    end else let 
      val (db, isnil) = demo_buffer_isnil (db)
      val db = demo_buffer_insert (db)
    in
      if isnil then conats_sharedn_signal (s, NOTFUL, db)
      else db
    end
  end
  
  val db = insert (db)
  val () = conats_shared_release (s, db); 
in
  producer (x)
end

// Keep removing elements from buffer.
fun consumer (x: int):<fun1> void = let
  val db = conats_shared_acquire (s)

  fun takeout (db: demo_buffer):<cloref1> demo_buffer = let
    val (db, isnil) = demo_buffer_isnil (db)
  in
    if isnil then let
      val db = conats_sharedn_condwait (s, NOTFUL, db)
    in
      takeout (db)
    end else let
      val (db, isful) = demo_buffer_isful (db)
      val db = demo_buffer_takeout (db)
    in
      if isful then let
        // Omitting the following would cause deadlock
        val db = conats_sharedn_signal (s, NOTEMP, db)
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
val tid3 = conats_tid_allocate ()


val () = conats_thread_create(producer, 0, tid1)
val () = conats_thread_create(consumer, 0, tid2)
val () = conats_thread_create(consumer, 0, tid3)

// List the properties for model checking.

%{$
#assert main deadlockfree;

// #assert main |= G sys_assertion;

%}

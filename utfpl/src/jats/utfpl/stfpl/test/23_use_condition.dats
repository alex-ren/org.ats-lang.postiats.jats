
#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"

(* ************* ************* *)
stacst mid: sid

extern val mc_m: mc_gv_t mid

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
val s = conats_shared_create {demo_buffer}(db)




fun foo1 (x: int):<fun1> void = let
  val db = conats_shared_acquire (s)
  val tid1 = conats_tid_allocate ()
  val () = conats_thread_create(foo2, 0, tid1)
  val db = lin_buffer_update (db, 1)
  val db = conats_shared_condwait (s, db)
  prval () = mc_set_int (mc_m, 1)
  val () = conats_shared_release (s, db); 
in
end

and foo2 (x: int):<fun1> void = let
  val db = conats_shared_acquire (s)
  val tid2 = conats_tid_allocate ()
  val () = conats_thread_create(foo3, 0, tid2)
  val db = lin_buffer_update (db, 2)
  val db = conats_shared_condwait (s, db)
  prval () = mc_set_int (mc_m, 2)
  val () = conats_shared_release (s, db); 
in
end

and foo3 (x: int):<fun1> void = let
  val db = conats_shared_acquire (s)
  val (db, len) = lin_buffer_get (db)
  prval () = mc_assert (len = 2)
  val db = conats_shared_signal (s, db)
  val () = conats_shared_release (s, db);
in
end

// Construct the model of whole system.
prval () = mc_set_int (mc_m, 0)
val () = foo1 (0)

// List the properties for model checking.

%{$

#define target mc_m == 1;
#assert main deadlockfree;

#assert main |= G sys_assertion;
#assert main reaches target;
%}

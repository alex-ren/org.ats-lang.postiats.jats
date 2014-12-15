
staload "./conats.sats"
staload UN = "prelude/SATS/unsafe.sats"

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

val lin_ref: lin_buffer int = lin_buffer_create (0)

val s = conats_shared_create {lin_buffer (int)}(lin_ref)

fun producer (x: int):<fun1> void = let
  val ref = conats_shared_acquire (s)

  fun loop (ref: lin_buffer int):<cloref1> void = let
    val (ref, v) = lin_buffer_get (ref)
  in
    if v = 2 then let
      val ref = conats_shared_condwait (s, ref)
    in
      loop (ref)
    end else let 
      val (ref, v) = lin_buffer_get (ref)
      val ref = lin_buffer_update (ref, v + 1)
      val ref = conats_shared_signal (s, ref)
    in
      loop (ref)
    end
  end
in
  loop (ref)
end

fun consumer (x: int):<fun1> void = let
  val ref = conats_shared_acquire (s)

  fun loop (ref: lin_buffer int):<cloref1> void = let
    val (ref, v) = lin_buffer_get (ref)
  in
    if v = 0 then let
      val ref = conats_shared_condwait (s, ref)
    in
      loop (ref)
    end else let
      val (ref, v) = lin_buffer_get (ref)
      val ref = lin_buffer_update (ref, v - 1)
      val ref = conats_shared_signal (s, ref)
    in
      loop (ref)
    end
  end
in
  loop (ref)
end

val tid1 = conats_tid_allocate ()
val tid2 = conats_tid_allocate ()

val () = conats_thread_create(producer, 0, tid1)
val () = conats_thread_create(consumer, 0, tid2)

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
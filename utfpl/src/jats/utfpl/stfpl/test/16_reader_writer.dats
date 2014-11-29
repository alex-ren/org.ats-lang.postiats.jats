
staload "./conats.sats"
staload UN = "prelude/SATS/unsafe.sats"

val gref = conats_atomref_create (0)
absviewtype lin_atomref (a:t@ype)

val lin_ref: lin_atomref int = $UN.castvwtp0 (gref)

val s = conats_shared_create {lin_atomref (int)}(lin_ref)

fun lin_atomref_update {a:t@ype} (
  lref: lin_atomref a, data: a): lin_atomref a = let
  val ref = $UN.castvwtp0 {atomref a} (lref)
  val () = conats_atomref_update (ref, data)
  val lref = $UN.castvwtp0 (ref)
in
  lref
end

fun lin_atomref_get {a:t@ype} (
  lref: lin_atomref a): (lin_atomref a, a) = let
  val ref = $UN.castvwtp0 {atomref a} (lref)
  val v = conats_atomref_get ref
  val lref = $UN.castvwtp0 (ref)
in
  (lref, v)
end

fun producer (x: int):<fun1> void = let
  val ref = conats_shared_acquire (s)

  fun loop (ref: lin_atomref int):<cloref1> void = let
    val (ref, v) = lin_atomref_get (ref)
  in
    if v = 1 then let
      val ref = conats_shared_condwait (s, ref)
    in
      loop (ref)
    end else let 
      val (ref, v) = lin_atomref_get (ref)
      val ref = lin_atomref_update (ref, v + 1)
      val ref = conats_shared_signal (s, ref)
      val () = conats_shared_release (s, ref)
    in
      producer (x)
    end
  end
in
  loop (ref)
end

fun consumer (x: int):<fun1> void = let
  val ref = conats_shared_acquire (s)

  fun loop (ref: lin_atomref int):<cloref1> void = let
    val (ref, v) = lin_atomref_get (ref)
  in
    if v = 0 then let
      val ref = conats_shared_condwait (s, ref)
    in
      loop (ref)
    end else let
      val (ref, v) = lin_atomref_get (ref)
      val ref = lin_atomref_update (ref, v - 1)
      val ref = conats_shared_signal (s, ref)
      val () = conats_shared_release (s, ref)
    in
      consumer (x)
    end
  end
in
  loop (ref)
end

val tid1 = conats_tid_allocate ()
val tid2 = conats_tid_allocate ()

val () = conats_thread_create(producer, 0, tid1)
val () = conats_thread_create(consumer, 0, tid2)


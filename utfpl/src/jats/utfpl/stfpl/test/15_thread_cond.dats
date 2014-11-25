

staload "./conats.sats"

val s = conats_shared_create{int}(0)

val (x,y) = (1, 2)

val a:int = conats_shared_acquire (s)
val a = conats_shared_signal (s, a)
val () = conats_shared_release (s, a)
val a = conats_shared_condwait (s, a)


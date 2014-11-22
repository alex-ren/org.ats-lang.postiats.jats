
staload "./conats.sats"


val g = conats_mutex_create()
val () = conats_mutex_acquire (g)
val () = conats_mutex_release (g)
val () = conats_mutex_acquire (g)
val () = conats_mutex_acquire (g)


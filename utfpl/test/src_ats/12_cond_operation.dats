
staload "./sys_model.sats"


val gm = sys_mutex_allocate ()
val gc = sys_cond_allocate ()

val () = sys_cond_wait (gc, gm)

val () = sys_cond_signal (gc)

val () = sys_cond_broadcast (gc)

val () = sys_cond_release (gc)


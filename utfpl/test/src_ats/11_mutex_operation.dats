
staload "./sys_model.sats"

val gm = sys_mutex_allocate ()

val () = sys_mutex_lock (gm)

val () = sys_mutex_unlock (gm)

val () = sys_mutex_release (gm)


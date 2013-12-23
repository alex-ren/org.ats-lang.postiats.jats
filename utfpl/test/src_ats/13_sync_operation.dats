
staload "./sys_model.sats"

val gv = sys_gvar_create (1)

val gm = sys_mutex_allocate ()
val gc = sys_cond_allocate ()

fun foo (x: int): void = let
  val () = sys_mutex_lock (gm)
  val () = sys_gvar_update (gv, x)
  val () = sys_mutex_unlock (gm)
in end

fun foo_cond (): void = let
  val () = sys_cond_wait (gc, gm)
  val () = sys_cond_signal (gc)
  val () = sys_cond_broadcast (gc)
  val () = sys_cond_release (gc)
in
end

fun xxf () = 3

val ret = xxf ()

val () = sys_mutex_lock (gm)

val () = sys_thread_create (1 (*thread id*), foo, 3 (*arg*))

val () = sys_mutex_unlock (gm)

val () = sys_gvar_update (gv, 2)




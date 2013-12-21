
staload "./sys_model.sats"

fun foo (x: int): void = let
  val () = sys_gvar_update (gv, x)
in end

val gv = sys_gvar_create (1)

val () = sys_thread_create (1 (*thread id*), 2)

val () = sys_gvar_update (gv, 3)


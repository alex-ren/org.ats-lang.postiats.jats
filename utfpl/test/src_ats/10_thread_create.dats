
staload "./sys_model.sats"


val gv = sys_gvar_create (1)

fun foo (x: int): void = let
  val () = sys_gvar_update (gv, x)
in end


val () = sys_thread_create (1 (*thread id*), foo, 2 (*arg*))

val () = sys_gvar_update (gv, 3)


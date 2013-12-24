
staload "./sys_model.sats"


val gvar1 = sys_gvar_create (2)
val gvar2 = sys_gvar_create (gvar1)
val () = sys_gvar_update (gvar2, 3)

fun foo (): void = let
  val () = sys_gvar_update (gvar2, 1)
in
end


fun kk (): void = ()


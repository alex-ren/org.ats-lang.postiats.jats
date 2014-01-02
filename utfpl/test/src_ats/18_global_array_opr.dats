
staload "./sys_model.sats"

val garr = sys_garr_create (3)

fun foo () = let
  val () = sys_garr_update (garr, 1, 99)
  val x = sys_garr_get (garr, 1)
in
end


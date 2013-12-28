
staload "./sys_model.sats"

fun length (xs, accu) = 
  if sys_list_is_nil (xs) then accu
  else let
    val xs = sys_list_get_tail (xs)
  in
    length (xs, accu + 1)
  end

val gn = sys_gvar_create (0)
val gn1 = sys_gvar_create (0)

fun foo () = let
  val xs = sys_list_nil ()
  val xs = sys_list_cons (1, xs)

  val tempx = length (xs, 0)
  
  val () = sys_gvar_update (gn, tempx)
  val () = sys_gvar_update (gn1, tempx)
  
in
  ()
end

val () = foo ()



                 
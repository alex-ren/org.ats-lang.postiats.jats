
staload "./sys_model.sats"

fun foo () = let
  val m = sys_mutex_allocate ()
in
  if 3 > 1 then let
    val () = sys_mutex_lock ()
  in
    ()
  end
  else let
    val () = sys_mutex_lock ()
  in
    ()
  end
end


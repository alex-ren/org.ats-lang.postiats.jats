

staload "./sys_model.sats"

val gn = sys_gvar_create (0)

val m = sys_mutex_allocate()
val c = sys_cond_allocate()

fun foo () = let
  fun loop () = let
    val () = sys_mutex_lock (m)
  in
    ()
  end
  
  val () = loop ()
  
in
  ()
end


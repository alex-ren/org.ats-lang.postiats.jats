
staload "./sys_model.sats"

val gn = sys_gvar_create (0)

val m = sys_mutex_allocate()
val c = sys_cond_allocate()


fun producer (x: int) = let
  val () = sys_mutex_lock (m)

  fun loop () =
    if sys_gvar_get (gn) = 1 then let
      val () = sys_cond_wait (c, m)
    in
      loop ()
    end else let 
      val () = sys_gvar_update (gn, sys_gvar_get (gn) + 1)
      val () = sys_cond_signal (c)
      val () = sys_mutex_unlock (m)
    in
      producer (x)
    end
in
  loop ()
end


fun consumer (x: int) = let
  val () = sys_mutex_lock (m)

  fun loop () = 
    if sys_gvar_get (gn) = 0 then let
      val () = sys_cond_wait (c, m)
    in
      loop ()
    end else let
      val () = sys_gvar_update (gn, sys_gvar_get (gn) - 1)
      val () = sys_cond_signal (c)
      val () = sys_mutex_unlock (m)
    in
      consumer (x)
    end
in
  loop ()
end

val () = sys_thread_create(1, producer, 0)
val () = sys_thread_create(2, consumer, 0)








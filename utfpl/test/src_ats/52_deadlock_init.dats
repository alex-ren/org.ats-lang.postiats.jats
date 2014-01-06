

staload "./sys_model.sats"

val garr = sys_garr_create (2)
val () = sys_garr_update (garr, 0, 0)
val () = sys_garr_update (garr, 1, 0)

val mutex1 = sys_mutex_allocate ()
val cond1 = sys_cond_allocate ()

val mutex2 = sys_mutex_allocate ()
val cond2 = sys_cond_allocate ()

fun wait_for_event (m, c, index, rcount) = let
  val () = sys_mutex_lock (m)
  val count = sys_garr_get (garr, index)
in
  if rcount = count then let
    val () = sys_cond_wait (c, m) 
  in
    sys_mutex_unlock (m)
  end else
    sys_mutex_unlock (m)
end

fun signal_event (m, c, index) = let
  val () = sys_mutex_lock (m)
  val count = sys_garr_get (garr, index)
  val count = count + 1
  val count = if count >= 3 then 0 else count
  val () = sys_garr_update (garr, index, count)
  val () = sys_cond_signal (c)
in
  sys_mutex_unlock (m)
end


fun task1_loop (count) = let
  val () = wait_for_event (mutex1, cond1, 0, count)
  val count = sys_garr_get (garr, 0)
  val () = signal_event (mutex2, cond2, 1)
in
  task1_loop (count)
end

fun task2_loop (count) = let
  val () = signal_event (mutex1, cond1, 0)
  val () = wait_for_event (mutex2, cond2, 1, count)
  val count = sys_garr_get (garr, 1)
in
  task2_loop (count)
end

fun task1 (arg) = let
  val count = 0
in
  task1_loop (count)
end
  
  
fun task2 (arg) = let
  val count = 0
in
  task2_loop (count)
end

val () = sys_thread_create (1, task1, 0)
val () = sys_thread_create (2, task2, 0)

%{$

#assert main deadlockfree;   
    
%}


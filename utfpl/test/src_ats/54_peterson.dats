(* ******** ********* *)

staload "./sys_model.sats"

(* ******** ********* *)

stacst csa: sid
stacst csb: sid
extern val csa: mc_gv_t (csa)
extern val csb: mc_gv_t (csb)

val victim = sys_gvar_create{int} (0)
val flag = sys_garr_create (2, 0)

val critical = sys_garr_create (2, 0)


fun lock (id: int): void = let
  val i = id
  val j = 1 - id
  val () = sys_garr_update (flag, i, 1)
  val () = sys_gvar_update (victim, i)
  fun loop (): void = 
    if sys_garr_get (flag, j) = 1 then let
      in
        if sys_gvar_get {int} (victim) = i then loop ()
        else sys_garr_update (critical, id, 1)
      end
    else sys_garr_update (critical, id, 1)
in
  loop ()
end

fun unlock (id: int): void = let
  val () = sys_garr_update (critical, id, 0)
  val () = sys_garr_update (flag, id, 0)
in end

assume thread_id_t = int

fun agent (id: int): void = let
  val () = lock (id)
  val () = unlock (id)
in
  agent (id)
end


val () = sys_thread_create (1 (*thread id*), agent, 0 (*arg*))
val () = sys_thread_create (2 (*thread id*), agent, 1 (*arg*))

%{$
#assert main deadlockfree;

#define 

#assert main |= G sys_assertion;

%}


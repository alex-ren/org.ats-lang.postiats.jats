

abstype global_variable_t
typedef gvar = global_variable_t

fun sys_gvar_create (x: int): gvar  // can only be called outside function
fun sys_gvar_update (gv: gvar, x: int): void

(* ************* ************** *)

abstype thread_id_t
typedef tid = thread_id_t

fun sys_tid_allocate (): tid

fun sys_thread_create (tid: tid, arg: int): void





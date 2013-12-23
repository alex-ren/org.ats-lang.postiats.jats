

abstype global_variable_t
typedef gvar = global_variable_t

fun sys_gvar_create (x: int): gvar  // can only be called outside function
fun sys_gvar_update (gv: gvar, x: int): void

(* ************* ************** *)

abstype thread_id_t
typedef tid = thread_id_t

fun sys_tid_allocate (): tid

fun sys_thread_create (tid: tid, arg: int): void

(* ************* ************** *)

abstype mutex_t
typedef mutex = mutex_t

fun sys_mutex_allocate (): mutex
fun sys_mutex_release (m: mutex): void
fun sys_mutex_lock (m: mutex): void
fun sys_mutex_unlock (m: mutex): void

(* ************* ************** *)

abstype cond_t
typedef cond = cond_t

fun sys_cond_allocate (): cond
fun sys_cond_release (c: cond): void
fun sys_cond_wait (c: cond, m: mutex): void
fun sys_cond_signal (c: cond): void
fun sys_cond_broadcast (c: cond): void



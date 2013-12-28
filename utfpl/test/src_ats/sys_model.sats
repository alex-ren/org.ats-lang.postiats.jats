

abstype global_variable_t
typedef gvar = global_variable_t

fun sys_gvar_create (x: int): gvar  // can only be called outside function
fun sys_gvar_update (gv: gvar, x: int): void
fun {a:t@ype} sys_gvar_get (gv: gvar): a

// fun {a:t@ype} sys_gvar_create (name: string, x: a): void  // can only be called outside function
// fun {a:t@ype} sys_gvar_update (name: string, x: a): void
// fun {a:t@ype} sys_gvar_get (name: string): a

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

(* ************* ************** *)

abstype sys_list_t
typedef sys_list = sys_list_t

fun sys_list_nil (): sys_list
fun sys_list_cons (x: int, xs: sys_list): sys_list
fun sys_list_get_header (xs: sys_list): int
fun sys_list_get_tail (xs: sys_list): sys_list
fun sys_list_is_nil (xs: sys_list): bool


















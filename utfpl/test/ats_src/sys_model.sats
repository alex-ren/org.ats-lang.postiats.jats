
// abstype global_value_t
// typedef gval = global_value_t

abstype global_variable_t
typedef gvar = global_variable_t

// fun sys_gval_create (x: int): gval

fun sys_gvar_create (x: int): gvar
fun sys_gvar_update (gv: gvar, x: int): void


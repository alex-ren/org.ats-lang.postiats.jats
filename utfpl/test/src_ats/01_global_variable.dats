
staload "./sys_model.sats"

val gvar2 = sys_gvar_create (2)

val x = 3 + sys_gvar_get<int> (gvar2)





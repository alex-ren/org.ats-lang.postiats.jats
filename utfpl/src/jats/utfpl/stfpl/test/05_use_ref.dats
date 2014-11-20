
staload "./conats.sats"


val g = conats_atomref_create(0)

val () = conats_atomref_update(g, 3)

val x:int = conats_atomref_get(g)

val () = mc_assert (x = 4)





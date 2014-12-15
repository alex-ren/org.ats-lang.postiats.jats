
staload "./conats.sats"


val g = conats_atomref_create {[x:int] int x} (0)

val () = conats_atomref_update(g, 3)

val x = conats_atomref_get(g)

val () = mc_assert (x = 4)


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}


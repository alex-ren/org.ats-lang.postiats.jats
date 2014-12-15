
staload "./conats.sats"


val g = 3

prval () = mc_assert (g > 4)


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

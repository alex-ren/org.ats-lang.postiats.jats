
#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"


val g = 3

prval () = mc_assert (g > 4)


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"

val g = conats_atomarrayref_create(2, 0)

val () = conats_atomarrayref_update(g, 1, 3)

val x:int = conats_atomarrayref_get(g, 1)

val () = mc_assert (x = 3)


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}


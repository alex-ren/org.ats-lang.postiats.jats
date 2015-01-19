#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"

// Create a global reference g.
val g = conats_atomref_create {[x:int] int x} (0)

// Set 3 to g.
val () = conats_atomref_update(g, 3)

val x = conats_atomref_get(g)

// The assertion would fail since x == 3.
val () = mc_assert (x = 4)


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}


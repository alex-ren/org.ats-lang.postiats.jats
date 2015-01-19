#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"


val x = if 1 > 2 then 3 else 4

prval () = mc_assert(x = 3)

fun foo ():<fun1> void = let
  val x = if 1 > 2 then 3 else 4
  prval () = mc_assert(x = 3)
in end

val () = foo ()

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

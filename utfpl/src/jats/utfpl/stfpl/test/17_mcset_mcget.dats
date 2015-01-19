
#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"


stacst mid: sid

extern val mc_m: mc_gv_t mid

fun foo (): void = let
  prval () = mc_set_int (mc_m, 3)
  prval (pf | x) = mc_get_int (mc_m)
  prval () = mc_assert (x = 3)
in
  mc_set_int (mc_m, 3)
end

val () = foo ()

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
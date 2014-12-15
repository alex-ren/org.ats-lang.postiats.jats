
staload "./conats.sats"

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
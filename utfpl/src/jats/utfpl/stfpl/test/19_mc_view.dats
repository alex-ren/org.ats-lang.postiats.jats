
staload "./conats.sats"

fun foo (): void = let
  prval v = mc_vlock_get (0, 0, 1, 1)
  prval v2 = mc_vlock_get (0, 0, 1, 2)
  prval () = mc_vlock_put (v)
  prval () = mc_vlock_put (v2)
in
end

val () = foo ()
%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

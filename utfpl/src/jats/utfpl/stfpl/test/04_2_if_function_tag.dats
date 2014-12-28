
staload "./conats.sats"

val ref = conats_atomref_create(0)

fun foo0 ():<fun1> void = let
  val x = true
in
  if x then conats_atomref_update (ref, 1) else conats_atomref_update (ref, 2)
end



%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

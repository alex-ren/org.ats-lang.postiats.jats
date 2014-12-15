

fun foo1 ():<cloref1> void = ()

fun foo2 ():<cloref1> int = let
  val x = foo1 ()
  val y = foo1
  // val z = y ()
in
  3
end

fun foo3 ():<cloref1> void = foo1 ()

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}


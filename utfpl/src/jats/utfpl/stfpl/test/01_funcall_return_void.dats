

fun foo1 ():<fun1> void = ()

fun foo2 ():<fun1> int = let
  val x = foo1 ()
in
  3
end

fun foo3 ():<fun1> void = foo1 ()


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}


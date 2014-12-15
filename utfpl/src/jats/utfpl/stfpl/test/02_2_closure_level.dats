
fun foo1 ():<cloref1> int = 3

fun foo2 ():<cloref1> int = let
  fun loop ():<cloref1> int = let
    val y = foo1 ()
  in
    y
  end
in
  3
end

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
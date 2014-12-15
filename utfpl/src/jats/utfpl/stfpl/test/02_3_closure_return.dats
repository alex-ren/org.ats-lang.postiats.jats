
val y = 0

fun foo1 ():<cloref1> int = let

  val a = 3

  fun foo2 ():<cloref1> (() -<cloref1> int) = let
    val b = a
    val c = y
    
  in
    foo1
  end
  
  val f = foo2 ()
 
  val x = y + 1
in
  x
end

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
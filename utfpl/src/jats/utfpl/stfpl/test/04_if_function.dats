
fun foo0 ():<fun1> bool = let
  val x = true
  val y = false
in
  x
end

fun foo1 (a: int):<fun1> int = let
  val x = if (1 > 2) then 1 else 2
  val y = if (foo0 ()) then 3 else 4
  val z = foo0 ()
  val r = x + y:int
in
  y
end

fun foox (): void = let
  val z = foo1 (0)
in
  ()
end 

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

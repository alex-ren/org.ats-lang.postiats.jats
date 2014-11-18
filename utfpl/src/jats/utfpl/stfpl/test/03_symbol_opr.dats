

fun foo1 (x: int, y: int):<fun1> int = x + y

fun foo2 (x: int):<fun1> int = let
  val z = foo1 (1, x)
in
  z
end

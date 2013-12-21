
fun foo1 (x, y): int = x
fun foo2 (x): void = ()

fun foo (z): int = let
  val x = foo1 (1, 2)
  val _ = foo2 (x)
  val () = foo2 (x)
in
  foo1 (4, 5)
end

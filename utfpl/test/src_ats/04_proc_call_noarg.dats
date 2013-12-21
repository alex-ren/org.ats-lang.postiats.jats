
fun foo1 (): int = 3
fun foo2 (): void = ()

fun foo () = let
  val x = foo1 ()
  val _ = foo2 ()
in
end

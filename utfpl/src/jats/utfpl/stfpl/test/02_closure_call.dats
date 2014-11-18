

fun foo1 (): void = ()

fun foo2 (): int = let
  val x = foo1 ()
in
  3
end

fun foo3 (): void = foo1 ()




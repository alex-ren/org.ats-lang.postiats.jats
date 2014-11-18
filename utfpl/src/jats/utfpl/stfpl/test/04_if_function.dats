
fun foo0 (): bool = true

fun foo1 (): int = let
  val x = if (1 > 2) then 1 else 2
  val y = if (foo0 ()) then 3 else 4
in
  x
end



fun foo (): int -<cloref1> int = let
  val x = 1
  fun foo1 (y: int): int = x
in
  foo1
end

fun foo2 ():void = let
  val x = 1
  fun foo1 (y: int):<cloref1> int = x
in
  ()
end
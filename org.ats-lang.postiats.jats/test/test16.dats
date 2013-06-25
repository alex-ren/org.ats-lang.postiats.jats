



staload "prelude/DATS/integer.dats"

fun foo (x: '(int, int)): int = x.0
fun foo2 (x: int): int = x

implement main0 () = let
  val a = '(1, 2)
  var a2 = '(3, 4)
  val b = foo a
  val c = foo2 (a.0)
  val c2 = foo2 (a2.0)
  val () = assertloc (b = 1 && c2 = 3)

in end

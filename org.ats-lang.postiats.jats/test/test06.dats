

staload "prelude/DATS/integer.dats"

fun foo (x: '(int, int)): int = x.0

implement main0 () = let
  val a = '(1, 2)
  val b = foo a

in end


staload "prelude/DATS/integer.dats"

fun foo_int (x: int): int = x

implement main0 () = let
  val a = 6
  var a2 = 7
  val b = foo_int a
  val b2 = a2
  val c = foo_int b2

in end

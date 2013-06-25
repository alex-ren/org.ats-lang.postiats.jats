
staload "prelude/DATS/integer.dats"

fun foo_int (x: int): int = x

implement main0 () = let
  var a1: int
  var a2: int = 2
  val b = foo_int 3
  val () = a1 := b
  val () = a2 := b
  val () = assertloc (a1 = a2)

in end

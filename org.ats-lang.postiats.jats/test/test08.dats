

staload "prelude/DATS/integer.dats"

fun foo (x: @(int, int)): int = x.0

implement main0 () = let
  var a = @(1, 2)
  val b = foo a
  val () = assertloc (b = 1)

in end


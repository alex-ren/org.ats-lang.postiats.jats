
staload "prelude/DATS/integer.dats"

fun foo (x: '(int, int)): int = x.0

implement main0 () = let
  var a = '(1, 2)
  val b = foo a

  val a1 = '(3, 4)
  val b1 = foo a1

  val () = assertloc (b1 = 3)


in end


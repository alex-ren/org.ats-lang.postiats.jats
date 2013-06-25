


staload "prelude/DATS/integer.dats"

typedef T = '(int, @(int, int))

fun foo (x: T): int = x.0

implement main0 () = let
  val i = @(4, 6): @(int, int)
  var a: T = '(1, i)
  val b = foo a

  val a1: T = '(1, @(2, 3))
  val b1 = foo a1

  val () = assertloc (b1 = 1)

in end

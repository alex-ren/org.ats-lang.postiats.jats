
staload "prelude/DATS/integer.dats"

extern fun foo_int (x: int): void

implement main0 () = let
  var x = '(1, 2)
  val () = x := '(2, 3)

  var x1 = @(1, '(2, 3)): @(int, '(int, int))
  var x2 = @(11, '(21, 31))
  val () = x1 := x2
  val () = assertloc (x2.1.0 = 21)

in end

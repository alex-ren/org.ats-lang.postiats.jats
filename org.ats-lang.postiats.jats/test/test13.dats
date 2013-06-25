
staload "prelude/DATS/integer.dats"

fun foo_int (x: int): int = x

implement main0 () = let
  var a1: int
  val b = foo_int 3
  val () = a1 := b
  var a2: int
  val () = a2 := a1

  val q = @(1, 2)
  var a2: @(int, int) = q
  val a3 = a2
  val _ = foo_int a3.0
  val () = a2 := @(3, 4)

  val () = a2.0 := 6
  
  var xx: @(int, int)
  val () = xx.0 := 3

  var yy: @(int, @(int, int))
  val () = yy.1.0 := 3
  val _ = foo_int (yy.1.0)

  val () = assertloc (yy.1.0 = 3)
in end



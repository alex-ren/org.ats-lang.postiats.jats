staload "prelude/DATS/integer.dats"



fun f91 (x: int): int =
  if x >= 101 then x - 10 else f91 (f91 (x+11))
// end of [f91]


implement main0 () = let
  // val () = foo ()

  val () = println! ("f91(10) = ", f91(10))
  val () = assertloc (f91 (0) = 91)

in end


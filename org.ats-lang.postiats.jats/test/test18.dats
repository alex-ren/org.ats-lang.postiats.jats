





staload "prelude/DATS/integer.dats"

fun foo2 (x: &int): int = let
  val () = x := 3
in
  3
end

implement main0 () = let
  var x: int = 3
  val y = foo2 x
in 
end



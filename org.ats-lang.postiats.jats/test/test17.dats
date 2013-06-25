




staload "prelude/DATS/integer.dats"

fun foo2 (x: int): int = x

implement main0 () = let
  val x = foo2 (3)
in 
  if x > 3 then assertloc (false) else () 
end



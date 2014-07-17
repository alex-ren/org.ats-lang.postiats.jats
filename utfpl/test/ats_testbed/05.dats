
/*
* Test about erasure.
*/

#include "share/atspre_staload.hats"

prfun foo1 .<>.(x: int, y: int): int = x + y
fun foo2 .<>.():<fun> (int, int) = (1, 2)

implement main0 () = let
  // prval (x, y) = foo2 ()
  val y = foo1 (1, 2)
  // val () = println! ("x is ", x)
  val () = println! ("y is ", y)
in
end



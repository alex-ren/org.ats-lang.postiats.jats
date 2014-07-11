#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

datatype dd =
| cons of (int, int)
| nil of ()

fun foo (cons (x, y): dd): int = 3

implement main0 () = let
  val x = nil ()
  // val _ = foo (nil ())
in
end


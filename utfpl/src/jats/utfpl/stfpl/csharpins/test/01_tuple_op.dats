
#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

fun foo1 (x: (int, (int, int)), y: int): (int, (int, int)) = (1, (2, 3))

fun foo1 (x: (int, (int, int)), y: int): int = let
  val (_, x) = x
  val (a, b) = x
in
  a
end

implement main0 () = let
  val () = print ("Helloworld");
in
end
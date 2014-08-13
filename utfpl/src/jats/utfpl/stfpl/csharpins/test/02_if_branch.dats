#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

fun foo (x: int, y: int): int =
  if (x > y) then x else y

implement main0 () = let
  val () = print ("Helloworld");
in
end
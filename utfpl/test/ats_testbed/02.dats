
// #include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

fun foo (f: int -<fun> int): int = f (3)

fun get_fun (x: int): int -<fun> int = let
  fun f.<>. (y: int):<fun> int = x + y
in
  f
end

val x = foo (get_fun (3))

implement main0 () = let
  val () = println! ("x = ", x)
in
end




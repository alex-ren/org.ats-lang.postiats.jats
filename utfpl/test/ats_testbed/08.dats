
#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

val x = 3
fun foo0 (): int = let
  val z = 3
  fun foo (y: int):<fun1> int = x + y + z
  val ret = foo (1)
in
  ret
end

implement main0 () = let
  val () = println! ("ret is ", foo0 ())
in
end



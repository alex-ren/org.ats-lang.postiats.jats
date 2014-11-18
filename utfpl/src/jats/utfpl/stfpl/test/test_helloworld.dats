
#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

staload "./foo.sats"

extern fun add (x: int, y: int): int


fun foo1 (x: (int, int), y: int): int = 3
fun foo2 (x: (int, int), y: int): int = foo1 (x, y) + y
fun foo3 (x: int): (int, int) = (3, x)

fun foo4 (x:int): int = add (x, 2) // 1 + 2

fun foo5 (x: int): void = ()

fun foo6 (): void = let
  val x = foo5 (3)
in
  x
end

implement main0 () = let
//   val () = foofoo1 ()
   val () = print ("Helloworld");
in
end


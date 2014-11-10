
#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

staload "./foo.sats"

extern fun add (x: int, y: int): int

fun foo1 (x: (int, int), y: int): int = 3
fun foo2 (x: (int, int), y: int): int = y
fun foo3 (x: int): (int, int) = (3, 3)

fun foo4 (): int = add (1, 2) // 1 + 2

implement main0 () = let
//   val () = foofoo1 ()
   val () = print ("Helloworld");
in
end


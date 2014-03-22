
(* ****** ****** *)
//
#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"
//
(* ****** ****** *)

absprop ddd

extern prfun foo (): (ddd | int)
prfun foo1 .<>. (): int = 1 + 2

fun boo.<>. (x: int):<fun> int = x

prfun foo2.<>. (): void = ()

implement main0 () = let
  val x = 3
  // prval (pf | x) = foo ()
  prval z = foo1()
  // prval xx = 1 + 2
  val y = boo (z)
  val () = print! 2
in
end


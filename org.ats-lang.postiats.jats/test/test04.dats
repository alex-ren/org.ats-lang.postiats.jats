
// staload "prelude/DATS/integer.dats"

fun foo1 (x: ptr): int = 3
fun foo2 (x: ptr): ptr = x

implement main0 () = let
  var p: ptr?
  val x = foo1 p

  val () = p := foo2 p
  

in end

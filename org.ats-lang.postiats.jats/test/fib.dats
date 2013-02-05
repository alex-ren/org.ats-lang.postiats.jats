//
staload _(*anon*) =
  "prelude/DATS/integer.dats"
// end of [staload]
staload _(*anon*) = "prelude/DATS/float.dats"
//
(* ****** ****** *)

typedef T = int
typedef T2 = (T, T)
fun fib (n: int): T = let
//
fun loop (
  xx: &T2, n: int
) : void = let
in
  if n > 0 then let
    val x0 = xx.0 and x1 = xx.1
    val () = xx.0 := x1 and () = xx.1 := x0+x1
  in
    loop (xx, n-1)
  end // end of [if]
end // end of [loop]
//
var xx: T2 = (0, 1)
val () = loop (xx, n)
//
in
  xx.0
end // end of [fib]

(* ****** ****** *)

implement
main () = 0 where {
  val ans = fib (10)
  val () = println! ("ans = ", ans)
} // end of [main]

(* ****** ****** *)

(* end of [fib.dats] *)

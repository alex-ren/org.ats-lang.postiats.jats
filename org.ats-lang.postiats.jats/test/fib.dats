//
staload _(*anon*) =
  "prelude/DATS/integer.dats"
// end of [staload]
staload _(*anon*) = "prelude/DATS/float.dats"
//
(* ****** ****** *)

typedef T = int
typedef T2 = (T, T)

extern fun fib (n: int): T

implement fib (n) = let
//
fun loop (
  xx: &T2, n: int
) : void = let
in
  if n > 0 then let
    val x0 = xx.0 and x1 = xx.1
//    val () = println! ("xx.0 = ", xx.0)
//    val () = println! ("xx.1 = ", xx.1)
    val () = xx.0 := x1 and () = xx.1 := x0+x1
//    val () = println! ("============")
  in
    loop (xx, n-1)
  end // end of [if]
end // end of [loop]
//
var xx: T2 = (0, 1)
val () = loop (xx, n)
// val () = println! ("xx.0 = ", xx.0)
//
in
  xx.0
end // end of [fib]

(* ****** ****** *)

implement
main () = 0 where {
  val ans = fib (3)
  val () = println! ("ans = ", ans)
  val () = assertloc (ans = 2)
} // end of [main]

(* ****** ****** *)

(* end of [fib.dats] *)

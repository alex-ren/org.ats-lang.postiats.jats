//
staload
_(*anon*) = "prelude/DATS/integer.dats"
//
(* ****** ****** *)

fun f91 (x: int): int =
  if x > 100 then x - 10 else f91 (f91 (x+11))
// end of [f91]

(* ****** ****** *)

implement
main (
) = 0 where {
  val () = println! ("f91(10) = ", f91(10))
}

(* ****** ****** *)

(* end of [f91p.dats] *)

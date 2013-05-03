//
staload
_(*anon*) = "prelude/DATS/integer.dats"
//
(* ****** ****** *)
//
// HX: an encoding of the factorial function:
// FACT (n, r) means that r equals the factorial of n
//
(* ****** ****** *)

dataprop FACT (int, int) =
  | FACTbas (0, 1) of ()
  | {n:pos}{r:int} FACTind (n, n*r) of FACT (n-1, r)
// end of [FACT]

(* ****** ****** *)

fun fact
  {n:nat} .<n>. (
  x: int n // the val of x is n
) : [r:int]
  (FACT (n, r) | int r) = let
in
//
if x > 0 then let
  val [r1:int]
    (pf1 | r1) = fact (x-1)
  prval pf = FACTind {n}{r1} (pf1)
  val r = x * r1
in
  (pf | r)
end else
  (FACTbas () | 1)
// end of [if]
//
end // end of [fact]

implement
main (
) = 0 where {
  val N = 12
  val ret = (fact(N)).1
  val () = println! ("fact(", N, ") = ", ret)
  val () = assertloc (ret = 479001600)
}
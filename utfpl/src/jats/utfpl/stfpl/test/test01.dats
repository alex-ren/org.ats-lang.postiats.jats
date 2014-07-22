(* ****** ****** *)
//
// Some simple tests for ATS -> JSON
//
(* ****** ****** *)

abstype OBJ

(* ****** ****** *)

fun fact (x: int): int =
  if x > 0 then x * fact (x-1) else 1

(* ****** ****** *)

val fact10 = fact (10)
and fact12 = fact (12)

(* ****** ****** *)

extern
fun acker (int, int): int

implement
acker (m, n) =
(
if m > 0
  then
    if n > 0
      then acker (m-1, acker (m, n-1)) else acker (m-1, 1)
    // end of [if]
  else n+1
// end of [if]
)

(* ****** ****** *)

fun isevn (n) = if n > 0 then isodd (n-1) else true
and isodd (n) = if n > 0 then isevn (n-1) else false

fun foo (n) = let
  val x = 3
  val y = x + 3
in
  y
end

(* ****** ****** *)

(* end of [test01.dats] *)

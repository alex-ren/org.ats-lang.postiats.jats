
//
// A gnumber-based implementation
//
// Author: Hongwei Xi
// Authoremail: gmhwxiATgmailDOTcom
// Time: the 30th of March, 2013
//
(* ****** ****** *)

staload
INT = "prelude/DATS/integer.dats"
staload
FLOAT = "prelude/DATS/float.dats"

(* ****** ****** *)

staload _ = "prelude/DATS/gorder.dats"
staload _ = "prelude/DATS/gnumber.dats"

(* ****** ****** *)

extern fun{a:t@ype} gfib (x: a): a

(* ****** ****** *)

implement
{a}(*tmp*)
gfib (x) = let
//
macdef ggt = ggt_val<a>
macdef ggte = ggte_val<a>
//
macdef gmul = gmul_val<a>
macdef gadd = gadd_val<a>
macdef gsub = gsub_val<a>
macdef gpred = gpred_val<a>
//
macdef gn = gnumber_int<a>
//
in
//
if x \ggte gn(2) then gfib<a> (gsub (x, gn(1))) \gadd gfib<a>(gsub (x, gn(2))) else x 
//
end // end of [gfib]

(* ****** ****** *)

implement
main0 () =
{
//
val () = println! ("fib(20) = ", gfib<int> (20))
val () = println! ("fib(20) = ", gfib<double> (20.0))
//
} // end of [main0]

(* ****** ****** *)

(* end of [gfib.dats] *)

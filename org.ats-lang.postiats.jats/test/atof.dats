//
// Converting a string to a double
//
// Author: Hongwei Xi (February 22, 2013)
//

(* ****** ****** *)
//
#include
"share/atspre_staload_tmpdef.hats"
//
(* ****** ****** *)

staload UN = "prelude/SATS/unsafe.sats"

(* ****** ****** *)

macdef double = g0int2float_int_double

(* ****** ****** *)

extern fun atof (str: string): double
extern fun atof_frac (str: string): double

(* ****** ****** *)

implement
atof (str) = let
//
typedef tenv = double
//
val str = string1_of_string0 (str)
//
var env: tenv = 0.0
implement{env}
string_foreach$cont (c, env) = isdigit (c)
implement
string_foreach$fwork<tenv>
  (c, env) = env := 10 * env + double(c - '0')
val n = string_foreach_env<tenv> (str, env)
//
val str2 =
  $UN.cast{string}(ptr_add<char>(string2ptr(str), n))
//
in
  env + atof_frac (str2)
end // end of [atof]

(* ****** ****** *)

implement
atof_frac (str) = let
//
fun loop
(
  p: ptr, num: double, den: double
) : double = let
  val c = $UN.ptr0_get<char> (p)
in
//
if isdigit (c) then
  loop (ptr_succ<char> (p), 10 * num + double(c - '0'), 10 * den)
else (num / den) // end of [if]
//
end // end of [loop]
//
val p0 = string2ptr (str)
val c0 = $UN.ptr0_get<char> (p0)
//
in
//
if c0 = '.' then loop (ptr_succ<char> (p0), 0.0, 1.0) else 0.0
//
end // end of [atof_frac]

(* ****** ****** *)

fn atof_usage
  (cmd: string): void = prerrln! ("Usage: ", cmd, " [float]")
// end of [atof_usage]

(* ****** ****** *)

implement
main0 (
  argc, argv
) = {
  val () =
    if (argc <= 1) then atof_usage (argv[0])
  val () = assertloc (argc >= 2)
  val () = println! ("atof(\"", argv[1], "\") = ", atof(argv[1]))
  val () = fprint_string(stdout_ref, "")
} // end of [main0]

(* ****** ****** *)

(* end of [atof.dats] *)

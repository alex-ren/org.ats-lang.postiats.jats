
(* ****** ****** *)

fun foo (x: string): void = ()

implement
main () = 0 where {
  var x: string = "123"
  val () = foo (x)
  val () = x := "456"
  val () = println! (x)
} // end of [main]

(* ****** ****** *)

(* end of [fib.dats] *)

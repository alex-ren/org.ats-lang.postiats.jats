
(* ****** ****** *)

fun foo (x: string): void = ()

implement
main () = 0 where {
  var x: string = "123"
  val () = foo (x)
  val () = x := "456"
  val () = println! (x)
   val () = assertloc (x = "456")
} // end of [main]

(* ****** ****** *)

(* end of [fib.dats] *)

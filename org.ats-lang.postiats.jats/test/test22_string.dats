

staload "prelude/DATS/integer.dats"

fun foo_val (s: string): void = ()
fun foo_var (s: &string): void = let
  val () = s := "good"
in end

implement main0 () = let
  val x1 = "abc": string
  val () = foo_val x1

  var x2 = "bcd"
  val () = foo_val x2
  val () = foo_var x2

  val x3 = (1, "def")
  val () = foo_val (x3.1)

  val () = assertloc (x2 = "good")



in end


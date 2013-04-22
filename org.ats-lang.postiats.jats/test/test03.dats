
staload "prelude/DATS/integer.dats"

fun foo_val (s: string): void = ()
fun foo_var (s: &string): void = let
  val () = s := "good"
in end

implement main0 () = let
  var x1 = "abc"
  val x2 = "bcd"
  val () = foo_val x2
  val () = x1 := x2
in end

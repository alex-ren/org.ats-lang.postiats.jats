
staload "./conats.sats"

fun foo .<>.(x: (int, (int, int))):<fun> int = let
  val (a, (b, c)) = x
in
  a + b * c
end

fun main():<fun1> void = let
  val x = foo ((1, (2, 3)))
  prval b = (x = 8)
  prval () = mc_assert (b)
in
end

val () = main()


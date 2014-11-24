staload "./conats.sats"

val x = if 1 > 2 then 3 else 4

prval () = mc_assert(x = 3)

fun foo ():<fun1> void = let
  val x = if 1 > 2 then 3 else 4
  prval () = mc_assert(x = 3)
in end

val () = foo ()


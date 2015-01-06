
fun foo (): (int | void) = (3 | ())

fun foo2 (): int = let
  val (x | _) = foo ()
  val (y | ()) = foo ()
  val (z | q) = foo ()
in
  x + y + z
end

prfun mc_goo .<>. (): (int | void) = (3 | ())

prfun mc_goo2 .<>.(): int = let
  prval (x | _) = mc_goo ()
  prval (y | ()) = mc_goo ()
  prval (z | q) = mc_goo ()
in
  x + y + z
end
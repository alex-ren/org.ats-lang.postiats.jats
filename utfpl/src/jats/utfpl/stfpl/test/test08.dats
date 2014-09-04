

absprop prop1_t

extern prfun gen_prop (): prop1_t

fun foo1 (): (prop1_t | int) = let
  val x: int = 3
  prval p = gen_prop ()
in
  (p | x)
end


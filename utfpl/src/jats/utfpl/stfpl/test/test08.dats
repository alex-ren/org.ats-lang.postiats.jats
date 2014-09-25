
/*
 * Test for removing proofs.
 */


absprop prop1_t

absprop prop2_t (int)

datatype dt_t (int) =
| {x:int} cons (x) of (prop2_t x | int)


extern prfun gen_prop (): prop1_t
// 
// fun foo1 (pf: prop1_t | x: int): (prop1_t | int) = let
//   val x: int = 3
//   prval p = gen_prop ()
//   val _ = foo1 (p | x)
// in
//   (p | x)
// end

fun foo2 (): @(prop1_t | int) = let
  prval p = gen_prop ()
in
  @(p | 3)
end

////
fun foo3 (): (int, int) = let
  fun foo4 (x: @(int, int)): void = ()
  val _ = foo4 ((3, 3))
  val (x, y) = foo3 ()
in
  (1, 1)
end



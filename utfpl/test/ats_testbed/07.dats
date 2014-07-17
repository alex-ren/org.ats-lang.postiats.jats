


absprop SUM (int, int)

extern prfun prfoo1 {x,y: int} (): SUM (x, y)

fun foo .<>. {x: int}(x: int x): [y: int] '(SUM (y, 3) | int, int) = let
  prval pf = prfoo1 {1, 3} ()
in
  '(pf | 1, 2)
end

fun foo1 (): int = let
  val '(pf | x, y) = foo (22)
in
  1
end

prfun foo2 .<>. (): int = 3

// fun foo3 (): [x,y:int] SUM (x, y) = prfoo1 {1, 2} ()
// error(4): [fun] should be replaced with [prfun] as this is a proof binding.

// The following still cannot compile.
fun foo4 (): [x,y:int] (SUM (x, y), void) = (prfoo1 {1, 2} (), ())

implement main0 () = let
  val x = foo1 ()
  val () = println! ("x = ", x)

  // val y = foo2 ()
  // val () = println! ("y = ", y)

  // val (x, y) = (3, foo4 ())

  prval prf = prfoo1 {1, 2} ()
  val (x, y) = (9, prf)
  val () = println! ("x = ", x)
in
end



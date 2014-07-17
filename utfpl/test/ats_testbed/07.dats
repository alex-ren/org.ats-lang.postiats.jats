


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

implement main0 () = let
  val x = foo1 ()
  val () = println! ("x = ", x)
in
end



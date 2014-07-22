
absprop SUM (int, int)

extern prfun prfoo1 {x,y: int} (): SUM (x, y)

prfun prfoo .<>. {x: int}(x: int x): [y: int] '(SUM (y, 3) | int, int) = let
  prval pf = prfoo1 {1, 3} ()
in
  (pf | 1, 2)
end

fun foo1 (): int = let
  val (pf | x, y) = prfoo (3)
in
  1
end



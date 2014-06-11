staload "libats/ML/SATS/basis.sats"

fun qq (x: int): int = 3

abstype ty (int, int)
extern fun foo {x: int} {y: int} (x: ty (x, y), y: int y): [q: int] int q

extern val vv:int

implement foo {x}{y}(x, y) = 3

extern fun ddd1 {a:type} (xs: a): int

fun list_add {a:type} (xs: a): int = 3

fun qq1 (x: int):<cloref1> int = 3

absprop propy (int)

prfun qq2 {x:int} .<>.(x: propy (3)): int = 3

val vv2: int = 1: int

implement vv = 3

fun foo3 (x: '(int, int)): '(int) = '(3)

fun {a:t@ype} foo4(x: a): int = 3
fun foo5 {a:t@ype} (x: a): a = x

extern fun {a:t@ype} foo6 (x: a): int

implement {a}foo6 (x) = 3

fun {a:t@ype} foo7 (x: a): a = let
  fun goo1 (y: a): int = 3
in
  x
end

 fun foo8 (xs: list0 (list0 int)): int = 3
 
 extern fun {a:t@ype} foo9 (x: a): int
 extern fun foo10 {a:type} (x: a): int






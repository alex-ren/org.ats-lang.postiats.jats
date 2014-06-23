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
 
fun foo11 {a:type} {x,y:int} .<x,y>.(): int = 3

datatype mylist (int, int) =
| {x:int}{y:int} mylist_cons (x, y) of (int x, int y, mylist (x - 1, y - 1))
| {x:int}{y:int} mylist_nil (x, y)

//fun foo12 {x,y:int} (x: mylist (x, y)): int =
//case+ x of
//| mylist_cons (x, y) => 1
//| mylist_nil () => 0

(*
 let
  val mylist_cons (x, y) = x
in
  3
end
*)

fun foo13 (x: int):<cloref1> int = 3
fun foo14 (x: int): int = 3

fun foo15 {a:type}{b:int}(): int = 3

fun foo16 (x: int, f: {a:type}{b:type}(a, b) -> int): int = f ()

fun foo17 (): int = foo16(1, foo16)
















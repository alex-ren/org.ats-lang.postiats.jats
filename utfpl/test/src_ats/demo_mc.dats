
(* ******** ********* *)

sortdef sid = int // id for state variable of model checking

stacst int_valueof: sid -> int

absprop int_value_of (sid, int)

abst@ype mc_gv_t (sid)

(* ******** ********* *)

extern fun mc_set_int {id: sid} (x: int):<fun> void

extern fun mc_set_int2 {id: sid} (id: (mc_gv_t id), x: int):<fun> void

extern fun mc_get_int {id: sid} ():<fun> [x: int | int_valueof (id) == x] int x

extern fun mc_get_int2 {id: sid} ():<fun> [x: int] (int_value_of (id, x) | int x)

extern fun mc_get_int3 {id: sid} (id: mc_gv_t id):<fun> [x: int] (int_value_of (id, x) | int x)

extern fun mc_assert {b: bool} (x: bool b):<fun> [b == true] void

(* ******** ********* *)

stacst g1: sid
stacst g2: sid

var cg1: mc_gv_t (g1)  // Have to use cg1, cannot use g1. This is weird since we can have "x: int x".

extern fun g2 ():<fun> mc_gv_t (g2)

(* ******** ********* *)

extern fun read {x: int | int_valueof (g1) == x && x > 3} ():<fun> int

extern fun read2 {x: int | x > 3} (int_value_of (g1, x)):<fun> int

(* ******** ********* *)

fun boo (): void = let
  val x = 3
  prval () = mc_set_int{g1} (x)
  prval () = mc_set_int (x)  // problem: This can also pass type checking.
                             // In this way, programmer may forget to set the 
                             // appropriate value for model checking.
in
end

fun boo2 (): void = let
  val x = 3
  prval () = mc_set_int2 (cg1, x)
  prval () = mc_set_int2 (g2 (), x)
in
end

(* ******** ********* *)

fun foo (): void = let
  prval [x: int] mc_x = mc_get_int {g1} ()  // Have to get access to x using fancy syntax.
  prval () = mc_assert (mc_x > 3)
  val _ = read {x} ()  // Have to specify x, which is a little cumbersome.
in
end


fun foo2 (): void = let
  prval (pf | mc_x) = mc_get_int2 {g1} ()  // Use proof.
  val () = mc_assert (mc_x > 3)
  val _ = read2 (pf)
in
end







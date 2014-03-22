
(* ******** ********* *)

staload "./sys_model.sats"

(* ******** ********* *)

(* ******** ********* *)

stacst g1: sid
stacst g2: sid

extern val g1: mc_gv_t (g1)
extern val g2: mc_gv_t (g2)


(* ******** ********* *)


absprop vprop
extern prfun gen_proof (): (vprop | )

fun read {x:int | x > 3} (pf1: vprop, pf: int_value_of (g1, x)| q: int, z: int): int = 999

(* ******** ********* *)

fun boo (): void = let
  val x = 3
  prval () = mc_set_int (g1, x)
  prval () = mc_set_int (g2, x)
  val y = 3 
in
end

(* ******** ********* *)

fun foo (): void = let
  prval (pf | mc_x) = mc_get_int (g1)  // Use proof.
  prval xx = mc_x + 3
  prval () = mc_assert (xx > 6)
  prval (pf1 | ) = gen_proof ()
  val _ = read (pf1, pf | 1, 2)
in
end





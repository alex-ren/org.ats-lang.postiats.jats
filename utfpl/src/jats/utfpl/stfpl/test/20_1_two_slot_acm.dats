// #define CONATSCONTRIB
// "https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
// staload "{$CONATSCONTRIB}/conats.sats"

staload "./conats.sats"

abstype slots_t (t@ype, int)

absviewtype own_slot_vt (int)

local
  assume slots_t (a:t@ype, x: int) = atomarrayref (a)
  assume own_slot_vt (i: int) = mc_vlock_vt (i, 0, 1, 1)
in
  fun slots_create {a:t@ype} {x:pos} (
    x: int x, v: a): slots_t (a, x) =
     conats_atomarrayref_create {a} (x, v)

  fun slots_update {a:t@ype} {x,i:nat | i < x} 
    ( vpf: own_slot_vt (i)
    | slots: slots_t (a, x), i: int i, v: a
    ): own_slot_vt i = let
    val () = conats_atomarrayref_update (slots, i, v)
  in
    vpf
  end

  fun slots_get {a:t@ype} {x,i:nat | i < x} 
    ( vpf: own_slot_vt (i)
    | slots: slots_t (a, x), i: int i
    ): (own_slot_vt i | a) = let
    val v = conats_atomarrayref_get (slots, i)
  in
    (vpf | v)
  end

  prfun acquire_ownership .<>. {i: nat}
    (i: int i): own_slot_vt (i) = mc_vlock_get (i, 0, 1, 1)

  prfun release_ownship .<>. {i: nat}
    (vpf: own_slot_vt (i)): void = mc_vlock_put (vpf)
  
end

typedef demo_slots_t = slots_t (int, 2)

val slots = slots_create (2, 0)



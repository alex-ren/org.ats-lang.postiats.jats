// #define CONATSCONTRIB
// "https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
// staload "{$CONATSCONTRIB}/conats.sats"

staload "./conats.sats"

// Define types needed by slots related operations.
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
    ): (own_slot_vt i | void) = let
    val () = conats_atomarrayref_update (slots, i, v)
  in
    (vpf | ())
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

// Define type for slots used in the example.
typedef demo_slots_t = slots_t (int, 2)

val slots = slots_create (2, 0)

val latest = conats_atomref_create {[x:nat | x < 2] int x} (0)

fun write (item: int): void = let
  val index = 1 - conats_atomref_get (latest)
  prval vpf = acquire_ownership (index)
  val (vpf | _) = slots_update (vpf | slots, index, item)
  prval () = release_ownship (vpf)
  val () = conats_atomref_update (latest, index)
in
end

fun read (): int = let
  val index = conats_atomref_get (latest)
  prval vpf = acquire_ownership (index)
  val (vpf | item) = slots_get (vpf | slots, index)
  prval () = release_ownship (vpf)
in
  item
end

fun loop_writer (x: int):<fun1> void = let
  val () = write (x)
in
  loop_writer (x)
end

fun loop_reader (x: int):<fun1> void = let
  val _ = read ()
in
  loop_reader (x)
end


// Construct the model of whole system.

val tid1 = conats_tid_allocate ()
val tid2 = conats_tid_allocate ()

val () = conats_thread_create(loop_reader, 0, tid1)
val () = conats_thread_create(loop_writer, 0, tid2)

// List the properties for model checking.

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}





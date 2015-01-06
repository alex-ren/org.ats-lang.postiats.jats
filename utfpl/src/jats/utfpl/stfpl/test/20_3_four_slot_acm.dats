// #define CONATSCONTRIB
// "https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
// staload "{$CONATSCONTRIB}/conats.sats"

staload "./conats.sats"

// Define types for data slots.
abstype dataslots_t (t@ype, int, int)

absviewtype own_slot_vt (int, int)

local
  assume dataslots_t (a:t@ype, x: int, y: int) = atomarrayref (atomarrayref (a))
  assume own_slot_vt (i: int, j: int) = mc_vlock_vt (i, j, 1, 1)
in
  // Create a two dimentional array
  fun dataslots_create {a:t@ype} {x,y:int| x>1 && y>1} (
    x: int x, y: int y, v: a): dataslots_t (a, x, y) = let
    val ele = conats_atomarrayref_create {a} (y, v)
    val array = conats_atomarrayref_create {atomarrayref a} (x, ele)

    fun loop (x: int, y: int, 
              array: atomarrayref (atomarrayref a),
              v: a): void =
      if x >= 0 then let
        val ele = conats_atomarrayref_create {a} (y, v)
        val () = conats_atomarrayref_update (array, x, ele)
      in
        loop (x - 1, y, array, v)
      end else ()

    val () = loop (x - 2, y, array, v)
  in
    array
  end

  fun dataslots_update {a:t@ype} {x,y,i,j:nat | i < x && j < y} 
    ( vpf: own_slot_vt (i, j)
    | slots: dataslots_t (a, x, y), i: int i, j: int j, v: a
    ): (own_slot_vt (i, j) | void) = let
    val ele = conats_atomarrayref_get (slots, i)
    val () = conats_atomarrayref_update (ele, j, v)
  in
    (vpf | ())
  end

  fun dataslots_get {a:t@ype} {x,y,i,j:nat | i < x && j < y} 
    ( vpf: own_slot_vt (i, j)
    | slots: dataslots_t (a, x, y), i: int i, j: int j
    ): (own_slot_vt (i, j) | a) = let
    val ele = conats_atomarrayref_get (slots, i)
    val v = conats_atomarrayref_get (ele, j)
  in
    (vpf | v)
  end

  prfun mc_acquire_ownership .<>. {i, j: nat}
    (i: int i, j: int j): own_slot_vt (i, j) = mc_vlock_get (i, j, 1, 1)

  prfun mc_release_ownership .<>. {i, j: nat}
    (vpf: own_slot_vt (i, j)): void = mc_vlock_put (vpf)
  
end

// Define type for data slots used in the example.
// We use four data slots in two dimentions.
typedef data_t = dataslots_t (int, 2, 2)
val data: data_t = dataslots_create (2, 2, 0)

typedef int2 = [i: int | i >= 0 && i <= 1] int i

// control variables
val slot = conats_atomarrayref_create {int2} (2, 0)
val latest = conats_atomref_create {int2} (0)
val reading = conats_atomref_create {int2} (0)

fun write (item: int): void = let
  val pair = 1 - conats_atomref_get (reading)
  val index = 1 - conats_atomarrayref_get (slot, pair)

  prval vpf = mc_acquire_ownership (pair, index)
  val (vpf | _) = dataslots_update (vpf | data, pair, index, item)
  prval () = mc_release_ownership (vpf)

  val () = conats_atomarrayref_update (slot, pair, index)
  val () = conats_atomref_update (latest, pair)
in
end

fun read (): int = let
  val pair = conats_atomref_get (latest)
  // Switch the following two steps would cause inconsistence.
  val () = conats_atomref_update (reading, pair)
  val index = conats_atomarrayref_get (slot, pair)

  prval vpf = mc_acquire_ownership (pair, index)
  val (vpf | item) = dataslots_get (vpf | data, pair, index)
  prval () = mc_release_ownership (vpf)
in
  item
end


fun loop_writer (x: int): void = let
  val () = write (x)
in
  loop_writer (x)
end

fun loop_reader (x: int): void = let
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






































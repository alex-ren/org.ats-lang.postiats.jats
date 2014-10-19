
staload "conats.sats"

staload "prelude/SATS/unsafe.sats"

abstype obj = ptr
extern fun obj_create (): obj

abstype sarray (int)

extern fun sarray_create (): [id: int] sarray (id)

val array = sarray_create ()

absview lockview (int, int, int)

// Exclusive view required.
extern fun sarray_set
  {id: int} {i,j: int}
  ( pf: !lockview (id, i, j) 
  | arr: sarray (id)
  , i: int i
  , j: int j
  , o: obj
  ): void

// Exclusive view required.
extern fun sarray_get
  {id: int} {i,j: int} 
  ( pf: !lockview (id, i, j) 
  | x: sarray (id), i: int i, j: int j
  ): obj


extern prfun lock_view_get 
  {id: int}{i:int}{j:int} 
  ( i: int i
  , j: int j
  ): lockview (id, i, j)

extern prfun lock_view_put 
  {id: int}{i:int}{j:int} 
  ( v: lockview (id, i, j)
  ): void

(* ********** ********** *)


stacst mw_init: sid
dataprop initialized =
| {x: int | x > 0} cons of (int_value_of (mw_init, x))

extern prval mw_init: mc_gv_t (mw_init)

val L = atomref_create (0)
val R = atomref_create (0)
val slot = atomarrayref_create (2, 0)

(* ******** ******** *)

(* ******** ******** *)

fun read_one (): obj = let
  val rp = atomref_get (L)
  val () = atomref_update (R, rp)
  val ri = atomarrayref_get (slot, rp)

  prval v = lock_view_get (rp, ri)  // model checking
  val o = sarray_get (v | array, rp, ri)
  prval () = lock_view_put (v)  // model checking
in
  o
end

fun write_one
  (o: obj): void = let
  val wp = 1 - atomref_get (R)
  val wi = 1 - atomarrayref_get (slot, wp)
  prval v = lock_view_get (wp, wi)  // model checking
  val () = sarray_set (v | array, wp, wi, o)
  prval () = lock_view_put (v)  // model checking
  val () = atomarrayref_update (slot, wp, wi)
  val () = atomref_update (L, wp)
in end

  prval () = mc_set_int (mw_init, 1)

(* *********** ************ *)

// Programmers state their assumption.
extern fun read_loop (pf: initialized): void

implement read_loop (pf) = let
  val x = read_one ()  // x is not used
in
  read_loop (pf)
end

fun reader (x: ptr): void = let
  prval (pfv | mw_init) = mc_get_int (mw_init)
  prval () = mc_assert (mw_init > 0)
  prval pf_init = cons (pfv)
in
  read_loop (pf_init)
end

(* *********** ************ *)

fun write_loop (): void = let
  val x = obj_create ()
  val () = write_one (x)
in
  write_loop ()
end

fun writer (x: ptr): void = let
  val () = write_loop ()
in end

(* ****************** ******************** *)

implement main0 () = let
  val x = obj_create ()
  val () = write_one (x)
  prval () = mc_set_int (mw_init, 1)

  val tid1 = sys_thread_create (reader, cast{ptr}(0) (*arg*))
  val tid2 = sys_thread_create (writer, cast{ptr}(0) (*arg*))
in
end

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}





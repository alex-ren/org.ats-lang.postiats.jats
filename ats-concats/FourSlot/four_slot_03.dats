

staload "conats.sats"

staload "prelude/SATS/unsafe.sats"

abstype obj = ptr  // to be ...
extern fun obj_create (): obj  // to be ...

abstype sarray (int)  // to be ...

extern fun sarray_create (): [id: int] sarray (id)  // to be ...

val array = sarray_create ()

absview lockview (int, int)

// Exclusive view required.
extern fun sarray_set  // to be ...
  {id: int} {i,j: int}
  ( pf: !lockview (i, j) 
  | arr: sarray (id)
  , i: int i
  , j: int j
  , o: obj
  ): void

// Exclusive view required.
extern fun sarray_get  // to be ...
  {id: int} {i,j: int} 
  ( pf: !lockview (i, j) 
  | x: sarray (id), i: int i, j: int j
  ): obj

// unsafe feature is used inside
// Programmer has responsibility.
prfun mc_lock_view_get
  {i,j:nat} .<>.
  ( i: int i
  , j: int j
  ): lockview (i, j) = let
  prval mc_v0 = mc_vlockview_get {2*i+j}{1} (2 * i + j, 1)
  prval v = castview0 {lockview (i, j)} (mc_v0)
in
  v
end

// unsafe feature is used inside
// Programmer has responsibility.
prfun mc_lock_view_put 
  {i,j:nat} .<>.
  ( v: lockview (i, j)
  | i: int i
  , j: int j
  ): void = let
  prval v0 = castview0 {mc_vlockview (2*i+j, 1)}(v)
  prval () = mc_vlockview_put (v0 | 2 * i + j, 1)
in end

(* ********** ********** *)
// Global values

stacst mw_init: sid
dataprop initialized =
| {x: int | x > 0} cons_init of (int_value_of (mw_init, x))

extern prval mc_init: mc_gv_t (mw_init)

typedef bin_t = [i:int|(i==0 || i==1)] int i
val L = atomref_create {bin_t}(0)
val R = atomref_create {bin_t}(0)
val slot = atomarrayref_create {bin_t}(2, 0)

(* ******** ******** *)

(* ******** ******** *)

fun read_one (): obj = let
  val rp = atomref_get (L)
  val () = atomref_update (R, rp)
  val ri = atomarrayref_get (slot, rp)

  prval v = mc_lock_view_get (rp, ri)  // model checking
  val o = sarray_get (v | array, rp, ri)
  prval () = mc_lock_view_put (v | rp, ri)  // model checking
in
  o
end

fun write_one
  (o: obj): void = let
  val wp = 1 - atomref_get (R)
  val wi = 1 - atomarrayref_get (slot, wp)
  prval v = mc_lock_view_get (wp, wi)  // model checking
  val () = sarray_set (v | array, wp, wi, o)
  prval () = mc_lock_view_put (v | wp, wi)  // model checking
  val () = atomarrayref_update (slot, wp, wi)
  val () = atomref_update (L, wp)
in end

  prval () = mc_set_int (mc_init, 1)

(* *********** ************ *)

// Programmers state their assumption.
extern fun read_loop (pf: initialized): void

implement read_loop (pf) = let
  val x = read_one ()  // x is not used
in
  read_loop (pf)
end

fun reader (x: ptr): void = let
  prval (pfv | mc_init0) = mc_get_int (mc_init)
  prval () = mc_assert (mc_init0 > 0)
  prval pf_init = cons_init (pfv)
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
  prval () = mc_set_int (mc_init, 1)

  val tid1 = sys_thread_create (reader, cast{ptr}(0) (*arg*))
  val tid2 = sys_thread_create (writer, cast{ptr}(0) (*arg*))
in
end

(* ****************** ******************** *)



(* ****************** ******************** *)

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

////






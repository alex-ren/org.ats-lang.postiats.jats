

staload "conats.sats"

staload "prelude/SATS/unsafe.sats"

abstype obj = ptr
extern fun obj_create (): obj

abstype sarray (int)

extern fun sarray_create (): [id: int] sarray (id)

val array = sarray_create ()

absview lockview (int, int)

// Exclusive view required.
extern fun sarray_set
  {id: int} {i,j: int}
  ( pf: !lockview (i, j) 
  | arr: sarray (id)
  , i: int i
  , j: int j
  , o: obj
  ): void

// Exclusive view required.
extern fun sarray_get
  {id: int} {i,j: int} 
  ( pf: !lockview (i, j) 
  | x: sarray (id), i: int i, j: int j
  ): obj

prfun mc_lock_view_get
  {i:int} {j:int} .<>.
  ( i: int i
  , j: int j
  ): lockview (i, j) = let
  prval mc_v0 = mc_vlockview_get (2 * i + j)
  prval v = castview0 {lockview (i, j)} (mc_v0)
in
  v
end

prfun mc_lock_view_put 
  {i:int}{j:int} .<>.
  ( v: lockview (i, j)
  | i: int i
  , j: int j
  ): void = let
  prval mc_x = 2 * i + j
  prval v0 = castview0 {mc_vlockview (2*i + j)}(v)
  prval () = mc_vlockview_put (v0 | mc_x)
in end

(* ********** ********** *)

stacst mw_init: sid
dataprop initialized =
| {x: int | x > 0} cons_init of (int_value_of (mw_init, x))

extern prval mc_init: mc_gv_t (mw_init)

val L = atomref_create (0)
val R = atomref_create (0)
val slot = atomarrayref_create (2, 0)

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












// extern fun malloc {a:t@ype} {n: int} 
// 
// fun read0 (): void =
//   // get pointer from channel
//   // call read
//   // delete pointer
// 
// 
// absviewtype ref (t@ype, int)
// 
// absview refview (addr, t@ype)
// absview lockview (addr, t@ype)
// 
// 
// 
// extern fun ptr_get (v: obj@p | p: ptr p): obj
// 
// extern fun ref_get
//   ( ref: !ref (obj, n) >> ref (obj, n-1)
//   ): [p: addr] (refview (p, obj) | ptr p)
// 
// extern prfun ref_put
//   ( v: refview (p, obj)
//   | ref: !ref (obj, n) >> ref (obj, n+1)
//   ): void 
// 
// extern prfun refview_get
// extern prfun refview_set
// 
// 
// 
// 
// fun read
//   ( p: ref (@[obj][n], r)
//   ): void = let
//     p: 
    

absvtype lock = ptr
extern fun lock_new (): lock
abstype shared (obj: vtype) // vtype for viewtype
//
extern fun shared_new {obj: vtype} (data: obj): shared (obj)
extern fun shared_acquire {obj:vtype} (sobj: shared (obj)): obj
extern fun shared_release {obj:vtype} (sobj: shared (obj), x: obj): void


fun foo (): void = let
  val s = shared_new {lock} (lock_new())
  val d = shared_acquire (s)
  val _ = shared_release (s, d)
in
end

extern fun mc_assert {p:addr}{a:type}(p: ptr p): (a@p | void)

abstype shared_unsafe (a: type)

extern fun get_ptr {a:t@ype} (shared_unsafe (a)): [p: addr] (ptr (p, a))

(* ************* ************** *)

absviewtype refcount (viewt@ype, int, addr) = ptr

extern fun refcount_create {a: viewt@ype} (x: a): refcount (a, 1)

extern fun refcount_get {a: viewt@ype} {n:int} 
  (ref: !refcount (a, n) >> refcount (a, n - 1)): a

extern fun refcount_put {a: viewt@ype} {n:int} 
  (ref: !refcount (a, n) >> refcount (a, n - 1), v: a)
  : refcount (a, n + 1)

extern fun refcount_delete {a: viewt@ype} 
  (ref: refcount (a, 0)): void

(* ************* ************** *)


fun TA (): void =
  val p = malloc {@[T][4]} ()






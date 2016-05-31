(* Author: Zhiqiang Ren
 * Last Modify: 11/03/2014
 *)

(* ************* ************** *)

// shared value

abstype shared_t (viewt@ype, int)
typedef shared (a:viewt@ype) = shared_t (a, 1)

fun conats_shared_create {a: viewt@ype} (ele: a): shared (a)
fun conats_sharedn_create {a: viewt@ype} {n:pos} (ele: a, n: int n): shared_t (a, n)
// fun conats_shared_finalize {a: viewt@ype} (s: shared a): a

fun conats_shared_acquire {a: viewt@ype} {n:pos} (s: shared_t (a, n)): a
fun conats_shared_release {a: viewt@ype} {n:pos} (s: shared_t (a, n), ele: a): void

fun conats_shared_signal {a: viewt@ype} (s: shared (a), ele: a): a
fun conats_sharedn_signal {a: viewt@ype} {i,n:nat | i < n} (s: shared_t (a, n), i: int i, ele: a): a

fun conats_shared_broadcast {a: viewt@ype} (s: shared (a), ele: a): a
fun conats_sharedn_broadcast {a: viewt@ype} {i,n:nat | i < n} (s: shared_t (a, n), i: int i, ele: a): a

fun conats_shared_condwait {a: viewt@ype} (s: shared (a), ele: a): a
fun conats_sharedn_condwait {a: viewt@ype} {i,n:nat | i < n} (s: shared_t (a, n), i: int i, ele: a): a

//absviewtype null_vt
//typedef cond = shared (null_vt)
//fun conats_shared_create_cond (): cond

(* ************* ************** *)

abstype mutex_t
typedef mutex = mutex_t

fun conats_mutex_create (): mutex
// fun conats_mutex_finalize (m: mutex): void

fun conats_mutex_acquire (m: mutex): void
fun conats_mutex_release (m: mutex): void

(* ************* ************** *)

abstype atomref (a: t@ype)

fun conats_atomref_create {a:t@ype} (data: a): atomref a
// fun conats_atomref_finalize {a:t@ype} (gv: atomref a): void
fun conats_atomref_update {a:t@ype} (gv: atomref a, data: a): void
fun conats_atomref_get {a:t@ype} (gv: atomref a): a

abstype atomarrayref (a: t@ype)

fun conats_atomarrayref_create {a:t@ype} (len: int, data: a): atomarrayref a
// fun conats_atomarrayref_finalize {a:t@ype} (gv: atomarrayref a): void
fun conats_atomarrayref_update {a:t@ype} (gv: atomarrayref a, pos: int, data: a): void
fun conats_atomarrayref_get {a:t@ype} (gv: atomarrayref a, pos: int): a

(* ************* ************** *)

abst@ype thread_id_t = int

typedef tid = thread_id_t

fun conats_tid_allocate (): tid
typedef thread_fun_t (a: t@ype) = (a -<fun1> void)
fun conats_thread_create {a:t@ype} (tfun: thread_fun_t a, arg: a, tid: tid): void

(* ************* ************** *)

// model checking related

sortdef mc_sid = int // id for state variable of model checking
sortdef sid = mc_sid

absprop int_value_of (sid, int)

abst@ype mc_gv_t (sid)

prfun mc_set_int {id: sid} {x: int} (id: (mc_gv_t id), x: int x): void

// symintr mc_set_int
// prfun mc_set_int1 {id: sid} (id: (mc_gv_t id), x: int): void
// prfun mc_set_int2 {id1,id2: sid} (
//             id1: (mc_gv_t id1), x1: int, 
//             id2: (mc_gv_t id2), x2: int
//             ): void
// prfun mc_set_int3 {id1,id2,id3: sid} (
//             id1: (mc_gv_t id1), x1: int, 
//             id2: (mc_gv_t id2), x2: int,
//             id3: (mc_gv_t id3), x3: int
//             ): void
// prfun mc_set_int4 {id1,id2,id3,id4: sid} (
//             id1: (mc_gv_t id1), x1: int, 
//             id2: (mc_gv_t id2), x2: int,
//             id3: (mc_gv_t id3), x3: int,
//             id4: (mc_gv_t id4), x4: int
//             ): void
// 
// overload mc_set_int with mc_set_int1
// overload mc_set_int with mc_set_int2
// overload mc_set_int with mc_set_int3
// overload mc_set_int with mc_set_int4

prfun mc_get_int {id1: sid} (
            id1: mc_gv_t id1
            ): [x1: int] (
            int_value_of (id1, x1) 
            | int x1)
// symintr mc_get_int
// prfun mc_get_int1 {id1: sid} (
//             id1: mc_gv_t id1
//             ): [x1: int] (
//             int_value_of (id1, x1) 
//             | int x1)
// 
// prfun mc_get_int2 {id1,id2: sid} (
//             id1: mc_gv_t id1,
//             id2: mc_gv_t id2
//             ): [x1,x2: int] (
//             int_value_of (id1, x1),
//             int_value_of (id2, x2) 
//             | 
//             int x1, 
//             int x2
//             )
// 
// prfun mc_get_int3 {id1,id2,id3: sid} (
//             id1: mc_gv_t id1,
//             id2: mc_gv_t id2,
//             id3: mc_gv_t id3
//             ): [x1,x2,x3: int] (
//             int_value_of (id1, x1), 
//             int_value_of (id2, x2), 
//             int_value_of (id3, x3) 
//             | 
//             int x1, 
//             int x2,
//             int x3
//             )
// 
// prfun mc_get_int4 {id1,id2,id3,id4: sid} (
//             id1: mc_gv_t id1,
//             id2: mc_gv_t id2,
//             id3: mc_gv_t id3,
//             id4: mc_gv_t id4
//             ): [x1,x2,x3,x4: int] (
//             int_value_of (id1, x1),
//             int_value_of (id2, x2),
//             int_value_of (id3, x3),
//             int_value_of (id4, x4)
//             | 
//             int x1,
//             int x2,
//             int x3,
//             int x4
//             )
// overload mc_get_int with mc_get_int1
// overload mc_get_int with mc_get_int2
// overload mc_get_int with mc_get_int3
// overload mc_get_int with mc_get_int4

(* ************* ************** *)

prfun mc_atomic_start (): void
prfun mc_atomic_end (): void

(* ************* ************** *)

prfun mc_assert {b: bool} (x: bool b):<fun> [b == true] void

(* ************* ************** *)

(* ************* ************** *)

absvtype mc_vlock_vt (int, int, int, int)

prfun mc_vlock_get {x,y: nat} {xi,yi: pos}
  ( x: int x
  , y: int y
  , xi: int xi
  , yi: int yi
  ): mc_vlock_vt (x, y, xi, yi)

prfun mc_vlock_put {x,y: nat} {xi,yi: pos}
  (v: mc_vlock_vt (x, y, xi, yi)
//  | x: int x
//  , y: int y
//  , xi: int xi
//  , yi: int yi
  ): void

(* ************* ************** *)

prfun mc_print {a:t@ype} (x: a): void


(* ************* ************** *)

abstype list_t (t@ype)
typedef list (a:t@ype) = list_t (a)

fun list_nil {a:t@ype} (): list a
fun list_cons {a:t@ype} (x: a, xs: list a): list a
fun list_get_header {a:t@ype} (xs: list a): a
fun list_get_element {a:t@ype} (xs: list a, n: int): a
// xs is not nil
fun list_remove_element {a:t@ype} (xs: list a, e: a): list a
fun list_get_tail {a:t@ype} (xs: list a): list a
fun list_is_nil {a:t@ype} (xs: list a): bool
fun list_length {a:t@ype} (xs: list a): int

(* ************* ************** *)



















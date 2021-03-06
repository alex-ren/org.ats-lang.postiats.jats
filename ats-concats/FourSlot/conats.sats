(* Author: Zhiqiang Ren
 * Last Modify: 11/03/2014
 *)

(* ************* ************** *)

// shared value

abstype shared_t (viewtype)
typedef shared (a:viewtype) = shared_t a

fun conats_shared_create {a: viewtype} (ele: a): shared (a)
// fun conats_shared_finalize {a: viewtype} (s: shared a): a

fun conats_shared_acquire {a: viewtype} (s: shared (a)): a
fun conats_shared_release {a: viewtype} (s: shared (a), ele: a): void
fun conats_shared_signal {a: viewtype} (s: shared (a)): void
fun conats_shared_condwait {a: viewtype} (s: shared (a), ele: a): void

absviewtype null_vt
typedef cond = shared (null_vt)
fun conats_shared_create_cond (): cond

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
typedef thread_fun_t (a: type) = (a -> void)
fun conats_thread_create {a:type} (tfun: thread_fun_t a, arg: a, tid: tid): void

(* ************* ************** *)

// model checking related

sortdef sid = int // id for state variable of model checking

absprop int_value_of (sid, int)

abst@ype mc_gv_t (sid)

prfun mc_set_int {id: sid} (id: (mc_gv_t id), x: int): void

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

fun negation {x: bool} (x: bool x):<fun0> bool (~x)

(* ************* ************** *)

absview mc_vlockview (int, int)

prfun mc_vlockview_get {i: nat} {j: pos}
  (i: int i, j: int j): mc_vlockview (i, j)

prfun mc_vlockview_put {i: nat} {j: pos} 
  ( v: mc_vlockview (i, j)
  | i: int i
  , j: int j
  ): void

(* ************* ************** *)

abstype sys_list_t
typedef sys_list = sys_list_t

fun sys_list_nil (): sys_list
fun sys_list_cons {a:type} (x: a, xs: sys_list): sys_list
fun sys_list_get_header {a:type} (xs: sys_list): a
fun sys_list_get_tail (xs: sys_list): sys_list
fun sys_list_is_nil (xs: sys_list): bool

(* ************* ************** *)



















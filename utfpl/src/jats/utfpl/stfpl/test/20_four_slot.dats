
staload "./conats.sats"


stacst mwp: sid
stacst mwi: sid
stacst min_write: sid
stacst mw_init: sid

extern val mwp: mc_gv_t (mwp)
extern val mc_mwi: mc_gv_t (mwi)
extern val mc_min_write: mc_gv_t (min_write)
extern val mc_mw_init: mc_gv_t (mw_init)

typedef int1 = [i:int] int i
val L = conats_atomref_create {int1} (0)
val R = conats_atomref_create {int1}(0)
val slot = conats_atomarrayref_create {int1}(2, 0)
val data = conats_atomarrayref_create {int1}(4, 0)

(* ******** ******** *)

(* ******** ******** *)

abst@ype data
extern fun data_create (): data

extern fun write (p: int, i: int, x: data): void

implement write (p, i, x) = ()

extern fun read {in_write:int} {wp,wi: int}
    {rp, ri: int| ~(in_write == 1 && wp == rp && wi == ri)} (
    pf0: int_value_of (min_write, in_write),
    pf1: int_value_of (mwp, wp),
    pf2: int_value_of (mwi, wi) | p: int rp, i: int ri): data


fun writer (x: data): void = let
  val wp = 1 - conats_atomref_get (R)
  val wi = 1 - conats_atomarrayref_get (slot, wp)

  prval () = mc_atomic_start ()
  prval () = mc_set_int (mwp, wp)
  prval () = mc_set_int (mc_mwi, wi)
  prval () = mc_set_int (mc_min_write, 1)
  prval () = mc_atomic_end ()

  val () = write (wp, wi, x)

  prval () = mc_set_int (mc_min_write, 0)
  
  val () = conats_atomarrayref_update (slot, wp, wi)
  val () = conats_atomref_update (L, wp)

  prval () = mc_set_int (mc_mw_init, 1)
in
end

fun comp3 .<>. {x,y,z:bool} (x: bool x, y: bool y, z: bool z):<fun> bool (x && y && z) =
//  x * y * z
if x then
  if y then
    if z then true
    else false
  else false
else false


absprop initialized

extern prfun is_initialized {w_init: int | w_init > 0} 
(pf: int_value_of (mw_init, w_init)): initialized

fun reader (pf: initialized): data = let
  val rp = conats_atomref_get (L)
  val () = conats_atomref_update (R, rp)
  val ri = conats_atomarrayref_get (slot, rp) // {[i:int] int i}

  prval () = mc_atomic_start ()
  prval (pfwp | wp) = mc_get_int (mwp)
  prval (pfwi | wi) = mc_get_int (mc_mwi)
  prval (pf_in | in_write) = mc_get_int (mc_min_write)
  prval () = mc_atomic_end ()

  prval () = mc_assert(~(comp3(in_write = 1, wp = rp, wi = ri)))
  
  val x = read (pf_in, pfwp, pfwi | rp, ri)
in
  x
end

fun loop_write (arg: int): void = let
  val () = writer (data_create ())
in
  loop_write (arg)
end

fun loop_read (arg: int): void = let
  prval (pf | w_init) = mc_get_int (mc_mw_init)
  prval () = mc_assert (w_init > 0)
  prval pf_init = is_initialized pf
  val x = reader pf_init
in
  loop_read (arg)
end


assume data = int
implement data_create () = 1
implement read {in_write} {wp,wi}
    {rp, ri} (pf0, pf1, pf2 | p, i) = data_create ()

(* ****************** ******************** *)

val () = writer (data_create ())

val tid1 = conats_tid_allocate ()
val tid2 = conats_tid_allocate ()

val () = conats_thread_create(loop_read, 0, tid1)
val () = conats_thread_create(loop_write, 0, tid2)


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}




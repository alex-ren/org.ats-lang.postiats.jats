
staload "./sys_model.sats"


stacst mwp: sid
stacst mwi: sid
stacst min_write: sid
stacst mw_init: sid

extern val mwp: mc_gv_t (mwp)
extern val mwi: mc_gv_t (mwi)
extern val min_write: mc_gv_t (min_write)
extern val mw_init: mc_gv_t (mw_init)

val L = sys_gvar_create{int} (0)
val R = sys_gvar_create{int} (0)
val slot = sys_garr_create (2, 0)
val data = sys_garr_create (4, 0)

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
  val wp = 1 - sys_gvar_get{int} (R)
  val wi = 1 - sys_garr_get (slot, wp)

  prval () = mc_set_int (mwp, wp, mwi, wi, min_write, 1)

  // val () = sys_garr_update (data, 2 * wp + wi, ~1)  // not important
  // val () = sys_garr_update (data, 2 * wp + wi, 1)  // not important
  val () = write (wp, wi, x)
  prval () = mc_set_int (min_write, 0)
  
  val () = sys_garr_update (slot, wp, wi)
  val () = sys_gvar_update (L, wp)

  prval () = mc_set_int (mw_init, 1)
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
(pf: int_value_of (mw_init, w_init) |): (initialized | )

fun reader (pf: initialized |): data = let
  // typedef myint = [i:int] int i
  val rp = sys_gvar_get{[i:int] int i} (L)
  val () = sys_gvar_update (R, rp)
  val ri = sys_garr_get (slot, rp)

  prval (pfwp, pfwi, pf_in | wp, wi, in_write) = 
    mc_get_int (mwp, mwi, min_write)

  prval () = mc_assert(negation(comp3(in_write = 1, wp = rp, wi = ri)))
  
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
  prval (pf | w_init) = mc_get_int (mw_init)
  prval () = mc_assert (w_init > 0)
  prval (pf_init |) = is_initialized (pf | )
  val x = reader (pf_init |)
in
  loop_read (arg)
end


assume data = int
implement data_create () = 1
implement read {in_write} {wp,wi}
    {rp, ri} (pf0, pf1, pf2 | p, i) = data_create ()

(* ****************** ******************** *)

val () = writer (data_create ())

assume thread_id_t = int

val () = sys_thread_create (1 (*thread id*), loop_write, 0 (*arg*))
val () = sys_thread_create (2 (*thread id*), loop_read, 0 (*arg*))

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
    
// datasort sidlist =
// | nil ()
// | cons (sid, sidlist)
// 
// datatype mc_gv_lst (xs: sidlst) =
// | nil ()
// | {x:sid}{xs:sidlst} cons (cons (x, xs)) of (mc_gv_t (x), mc_gv_lst (xs))
// 
// prfun mc_get_int_list {ids: sidlst} (ids: mc_gv_lst ids): [xs: ilist]
//   (int_list_value_of (ids, xs) | list x)





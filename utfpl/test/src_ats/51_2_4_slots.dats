
staload "./sys_model.sats"

sortdef m_sym = int

dataprop modelv (m_sym, t@ype) = {id:m_sym}{a:t@ype} assign (id, a)
dataprop modelv_int (m_sym, int) = {id:m_sym}{v:int} assign (id, v)
  
extern prfun mc_set {a:t@ype} (id: string, x: a): void
extern prfun mc_set_int {x: int} (id: int, x: int x): void

extern prfun mc_get {a:t@ype} {id:int} (id: int id): (modelv (id, a) | a)
// extern prfun mc_get {id:int} (id: int id): [a:t@ype] (modelv (id, a) | a)
extern prfun mc_get_int {id:m_sym} (id: int id): [v:int](modelv_int (id, v) | int v)

extern prfun mc_set_flag (id: string): void
extern prfun mc_get_flag (id: string): [b: bool] bool b
extern prfun mc_reset_flag (id: string): void

extern prfun mc_assert {b: bool} (x: bool b): [b == true] void

extern prfun mc_is_set_flag {pf: prop} (id: string): pf

val L = sys_gvar_create{int} (0)
val R = sys_gvar_create{int} (0)
val slot = sys_garr_create (2, 0)
val data = sys_garr_create (4, 0)

(* ******** ******** *)

#define swp 1
#define swi 2

(* ******** ******** *)

typedef myint = [i:int] int i

abstype data
extern fun data_create (): data
extern fun write (p: int, i: int, x: data): void
extern fun read {min_write:bool} 
                {mwp: int} {mwi:int} 
                {rp, ri: int| ~(min_write && mwp == rp && mwi == ri)} (
                pf1: modelv (swp, (* todo: [i:int] int i *) myint), pf2: modelv_int (swi, mwi) | 
  p: int rp, i: int ri): data


fun writer (x: data): void = let
  val wp = 1 - sys_gvar_get{int} (R)
  val wi = 1 - sys_garr_get (slot, wp)

  prval () = mc_set("wp", wp)
  prval () = mc_set("wi", wi)
  prval () = mc_set_flag("in_write")
  val () = sys_garr_update (data, 2 * wp + wi, ~1)  // not important
  val () = sys_garr_update (data, 2 * wp + wi, 1)  // not important
  prval () = mc_reset_flag("in_write")
  
  val () = write (wp, wi, x)
  
  val () = sys_garr_update (slot, wp, wi)
  val () = sys_gvar_update (L, wp)
  prval () = mc_set_flag("w_init")
in
end

extern fun comp3 {x,y,z:bool} (x: bool x, y: bool y, z: bool z):<> bool (x && y && z)

absprop initialized

fun reader (pf: initialized): data = let
  
  val rp = sys_gvar_get{myint} (L)
  val () = sys_gvar_update (R, rp)
  val ri = sys_garr_get (slot, rp)

  prval (pfwp | [mwp:int] mwp) = mc_get {(* todo: [i: int] int i*) myint} (swp)
  // prval (pfwp | [mwp:int] mwp) = mc_get {[i: int] int i} (swp)

  prval [mwi:int] (pfwi | mwi) = mc_get_int (swi)

  prval [min_write:bool] min_write = mc_get_flag("in_write")
  prval () = mc_assert(~comp3(min_write = true, mwp = rp, mwi = ri))
  
  val x = read{min_write} {mwp} (pfwp, pfwi | rp, ri)
in
  x
end

fun loop_write (arg: int): void = let
  val () = writer (data_create ())
in
  loop_write (arg)
end

fun loop_read (arg: int): void = let
  prval mpf = mc_is_set_flag{initialized} ("w_init")
  val x = reader (mpf)
in
  loop_read (arg)
end


val () = writer (data_create ())

assume thread_id_t = int

val () = sys_thread_create (1 (*thread id*), loop_write, 0 (*arg*))
val () = sys_thread_create (2 (*thread id*), loop_read, 0 (*arg*))

%{$
#assert main deadlockfree;

#define Prop1 (content == 1);
#define Prop2 (content != 1);
#define Prop3 (content != -1);
#define Prop4 (content != -2);

#assert main |= (G Prop2) || (F G Prop1);
#assert main |= G Prop3;
#assert main |= F G Prop1;

#assert main |= G Prop4;

%}
    






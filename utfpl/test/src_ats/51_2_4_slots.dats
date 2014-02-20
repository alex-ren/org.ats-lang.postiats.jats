
staload "./sys_model.sats"

val L = sys_gvar_create (0)
val R = sys_gvar_create (0)
val slot = sys_garr_create (2, 0)
// val data = sys_garr_create (4, 0)

abstype data
extern fun data_create (): data
extern fun write (p: int, i: int, x: data): void
extern fun read (p: int, i: int): data

fun writer (x: data): void = let
  val wp = 1 - sys_gvar_get (R)
  val wi = 1 - sys_garr_get (slot, wp)

  mcval () = mc_set("wp", wp)
  mcval () = mc_set("wi", wi)
  mcval () = mc_set_flag("in_write")
  val () = sys_garr_update (data, 2 * wp + wi, ~1)  // not important
  val () = sys_garr_update (data, 2 * wp + wi, 1)  // not important
  mcval () = mc_reset_flag("in_write")
  
  val () = write (wp, wi, x)
  
  val () = sys_garr_update (slot, wp, wi)
  val () = sys_gvar_update (L, wp)
  mcval () = mc_set_flag("w_init")
in
end

fun reader (): data = let
  mcval () = mc_is_set_flag("w_init")
  
  val rp = sys_gvar_get (L)
  val () = sys_gvar_update (R, rp)
  val ri = sys_garr_get (slot, rp)
  
  mcval mwp = mc_get("wp")
  mcval mwi = mc_get("wi")
  mcval min_write = mc_get_flag("in_write")
  mcval () = mc_assert(~(min_write && mwp == rp && mwi = ri))
  
  val x = read (rp, ri)
in
  x
end

fun loop_write (arg: int): void = let
  val () = writer (data_create ())
in
  loop_write (arg)
end

fun loop_read (arg: int): void = let
  val x = reader ()
in
  loop_read (arg)
end


val () = writer (data_create ())

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
    







staload "./sys_model.sats"

val L = sys_gvar_create (0)
val R = sys_gvar_create (0)
val slot = sys_garr_create (2, 0)
val data = sys_garr_create (4, 0)

val content = sys_gvar_create (0)

fun writer (x: int): void = let
  val wp = 1 - sys_gvar_get (R)
  val wi = 1 - sys_garr_get (slot, wp)
  val () = sys_garr_update (data, 2 * wp + wi, ~1)
  val () = sys_garr_update (data, 2 * wp + wi, 1)
  val () = sys_garr_update (slot, wp, wi)
  val () = sys_gvar_update (L, wp)
in
end

fun reader (): int = let
  val rp = sys_gvar_get (L)
  val () = sys_gvar_update (R, rp)
  val ri = sys_garr_get (slot, rp)
  val x = sys_garr_get (data, 2 * rp + ri)
in
  x
end

fun loop_write (arg: int): void = let
  val () = writer (1)
in
  loop_write (arg)
end

fun loop_read (arg: int): void = let
  val () = sys_gvar_update (content, reader ())
in
  loop_read (arg)
end

val () = sys_thread_create (1 (*thread id*), loop_write, 0 (*arg*))
val () = sys_thread_create (2 (*thread id*), loop_read, 0 (*arg*))

%{$
#assert main deadlockfree;

#define Prop1 (content == 1);
#define Prop2 (content != 1);
#define Prop3 (content != -1);

#assert main |= (G Prop2) || (F G Prop1);
#assert main |= G Prop3;
#assert main |= F G Prop1;

%}
    






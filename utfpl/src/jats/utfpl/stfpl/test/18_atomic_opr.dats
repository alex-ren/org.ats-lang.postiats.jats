
staload "./conats.sats"

stacst mid: sid

extern val mc_m: mc_gv_t mid

fun foo1 (): void = let
  prval () = mc_atomic_start()
  prval () = mc_set_int (mc_m, 3)
  prval () = mc_set_int (mc_m, 4)
  prval () = mc_atomic_end()
in
end

fun foo2 (x: int): void = let
  prval (pf | x) = mc_get_int (mc_m)
  prval () = mc_assert (x <> 3)
in
end

val tid1 = conats_tid_allocate ()

val () = conats_thread_create(foo2, 0, tid1)

val () = foo1 ()

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
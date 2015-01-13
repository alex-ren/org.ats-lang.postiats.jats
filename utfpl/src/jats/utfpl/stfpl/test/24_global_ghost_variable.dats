//#define CONATSCONTRIB
//"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
//staload "{$CONATSCONTRIB}/conats.sats"

staload "./conats.sats"

stacst sid_init: sid
extern val mc_init: mc_gv_t sid_init


fun exec (x: int): void = let

  fun foo {init: pos}(pf: int_value_of (sid_init, init) | x: int): int = x

  prval (pf | init) = mc_get_int (mc_init)
  
  // mc_assert cannot be omitted though it is ghost code.
  // prval () = mc_assert (init > 0)
  
  val _ = foo (pf | x)
in
end

val tid1 = conats_tid_allocate ()

val () = conats_thread_create(exec, 0, tid1)

prval () = mc_set_int (mc_init, 1)


%{$
// #assert main deadlockfree;

#assert main |= G sys_assertion;

%}


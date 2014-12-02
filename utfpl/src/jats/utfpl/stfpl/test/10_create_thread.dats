
staload "./conats.sats"
staload UN = "prelude/SATS/unsafe.sats"

val gref = conats_atomref_create (0)

fun foo (x: int): void = let
  val () = conats_atomref_update (gref, x)
in
end

val tid1 = conats_tid_allocate ()

val () = conats_thread_create(foo, 1, tid1)

val y = conats_atomref_get (gref)

prval () = mc_assert (y = 0)


%{$

%}


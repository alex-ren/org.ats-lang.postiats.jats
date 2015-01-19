

#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"


val g = conats_atomref_create(0)


fun fact0 (x: int):<fun1> void = let
  val r = fact (x)
  val () = conats_atomref_update(g, r)
in end

and fact (x: int):<fun1> int = 
  if x <= 1 then 1
  else x * fact (x - 1)

val tid = conats_tid_allocate()

val () = conats_thread_create(fact0, 3, tid)

val x:int = conats_atomref_get(g)

val () = mc_assert (x <> 6)

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}


#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"


val s = conats_shared_create (0)


val a = conats_shared_acquire (s)
val a = conats_shared_signal (s, a)
val a = conats_shared_condwait (s, a)
val () = conats_shared_release (s, a)
%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

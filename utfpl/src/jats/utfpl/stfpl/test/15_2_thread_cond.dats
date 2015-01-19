
#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"


val s = conats_sharedn_create (0, 2)


val a = conats_shared_acquire (s)
val a = conats_sharedn_signal (s, 0, a)
val a = conats_sharedn_signal (s, 1, a)
//val a = conats_sharedn_condwait (s, 0, a)
val () = conats_shared_release (s, a)

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
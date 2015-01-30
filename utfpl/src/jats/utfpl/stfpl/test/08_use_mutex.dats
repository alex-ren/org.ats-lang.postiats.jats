
#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"

fun foo1(x: int): int = x + 1

fun foo (): void = let
val q = foo1 (3)
val g = conats_mutex_create()
val () = conats_mutex_acquire (g)
val () = conats_mutex_release (g)
val () = conats_mutex_acquire (g)
val () = conats_mutex_acquire (g)
in
end

val () = foo ()

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
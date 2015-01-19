#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"

fun foo .<>.(x: (int, (int, int))):<fun> int = let
  val (a, (b, c)) = x
in
  a + b * c
end

fun main():<fun1> void = let
  val x = foo ((1, (2, 3)))
  prval b = x = 7
  prval () = mc_assert (b)
in
end

val () = main()

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
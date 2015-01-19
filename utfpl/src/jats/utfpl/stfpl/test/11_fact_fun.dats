#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"


fun fact1 (x: int):<fun1> int =
  if x <= 1 then 1
  else let
    val y = fact1 (x - 1)
  in
    x * y
  end

prfun mc_fact2 .<>. (x: int, accu: int):<fun1> int =
  if x <= 1 then accu
  else mc_fact2 (x - 1, x * accu)

fun main():<fun1> void = let
  val x = fact1 4
  prval y = mc_fact2 (4, 1)
  prval () = mc_assert (x = y)
  prval () = mc_assert (x = 23)
in
end

val () = main()

%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}
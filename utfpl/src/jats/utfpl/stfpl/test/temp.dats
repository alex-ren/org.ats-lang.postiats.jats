
// #define CONATSCONTRIB
// "https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
// staload "{$CONATSCONTRIB}/conats.sats"
staload "./conats.sats"

(* ************* ************* *)

typedef Int = [x:int] int x
  
fun sum {x:nat} (x: int x): Int = x * (x + 1) / 2

fun sum2 {x:nat}.<x>.(x: int x):<fun> Int = 
  if x > 0 then x + sum2 (x - 1)
  else 0

val n = 3
val ret = sum (n)
prval mc_ret = sum2 (n)
prval () = mc_assert (ret = mc_ret)

prval () = mc_print (ret)
prval () = mc_print (mc_ret)

%{$

#assert main |= G sys_assertion;

%}


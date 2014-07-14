
// #include "share/atspre_define.hats"
// #include "share/atspre_staload.hats"


abstype OBJ

(* ****** ****** *)

fun fact (x: int): int = 
  if x > 0 then x * fact (x-1) else 1


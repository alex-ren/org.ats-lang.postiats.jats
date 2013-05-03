


#include
"share/atspre_staload_tmpdef.hats"

typedef rec1 = @{x1 = int, x2 = int}
typedef rec2 = @{x1 = int, x2 = rec1}

fun foo (): void =
{
var tt = @{x1 = 1, x2 = @{x1=2, x2=3} }: rec2
val () = tt.x1 := 11
val () = tt.x2.x1 := 22
val () = tt.x2 := @{x1=22, x2=33}
val () = assertloc (tt.x1 = 11)
val () = assertloc (tt.x2.x1 = 22)
val () = assertloc (tt.x2.x2 = 33)



} // end of [foo]

implement main0 () = foo ()




#include
"share/atspre_staload_tmpdef.hats"

fun foo (): void =
{
val A = (arrayptr)$arrpsz{@(int, int)}((1, 1), (2, 2))
val x = A[0].0
val () = A[0].0 := 1

// val () = assertloc (A[0] = 3)
// val () = assertloc (A[1] = 4)
// val () = assertloc (A[2] = 5)
val () = arrayptr_free (A)

var tt = @(1, (2, 3)): @(int, @(int, int))
val () = tt.0 := 3
val () = tt.1.0 := 3



} // end of [foo]

implement main0 () = foo ()




#include
"share/atspre_staload_tmpdef.hats"

fun foo (): void =
{
val A = (arrayptr)$arrpsz{int}(3, 4, 5)
val () = assertloc (A[0] = 3)
val () = assertloc (A[1] = 4)
val () = assertloc (A[2] = 5)
val () = arrayptr_free (A)
} // end of [foo]

implement main0 () = foo ()

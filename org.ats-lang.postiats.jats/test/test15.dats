

#include
"share/atspre_staload_tmpdef.hats"

fun foo (): void =
{
val A = (arrayptr)$arrpsz{int}(0, 1, 2)
val () = assertloc (A[0] = 0)
val () = assertloc (A[1] = 1)
val () = assertloc (A[2] = 2)
val () = arrayptr_free (A)
} // end of [foo]

implement main0 () = ()

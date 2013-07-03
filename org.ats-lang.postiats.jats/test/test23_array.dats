
#include
"share/atspre_staload_tmpdef.hats"

fun foo (): void =
{
val A = (arrayptr)$arrpsz{int}(3, 4, 5)
// val x = '(1, A)
// val b = x.1
val b = A
val c = b[0]
val () = assertloc (c = 3)
val () = arrayptr_free (b)
} // end of [foo]

implement main0 () = foo ()


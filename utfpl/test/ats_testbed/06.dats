
/*
* test about closure
*/

// #include "share/atspre_staload.hats"

fun foo (): void = let
  val x = 6
  extern fun foo1 (): int

  implement foo1 () = x
  
  val y = foo1 ()
  val () = println! ("y = ", y)

  fun foo2 (): int = x

  val z = foo2 ()
  val () = println! ("z = ", z)
in
end

implement main0 () = let
  val () = foo ()

in
end


/*
Note:
Only templates can be legally implemented inside
the body of a function:

Toplevel closures are still allowed but they cannot be implemented inside
the body of a function. So they are not very useful.
*/





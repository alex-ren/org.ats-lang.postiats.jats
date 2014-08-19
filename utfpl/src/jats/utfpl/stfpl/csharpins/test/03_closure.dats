#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

fun foo ():<fun1> void = let
  val x: int = 3
  fun foo1 ():<fun1> void = let
    val y = 1
  in
  end
in
end


// Call a function via name.
fun foo1 (x: int):<fun1> int = let
  val x1: int = 1 + 2
  val x2 = foo1 (x)
in
  x1
end

// todo: call a function via object.

// Call a closure via object.
fun foo2 (f: int-<fun1> int): int = let
  val x = 1
  val y = f (x)
in
  y
end


fun foo3 (x: int): int = let
  val x = 1
  fun foo4 (y: int): int = y + x
  val y = foo4 (2)
in
  y
end

// indented closure
fun foo5 (): void = let
  val x = 1
  fun foo6 (): void = let
    val y = x
    fun foo7 (): void = let
      val z:int = x + y
    in
    end
  in
  end
in
end

fun foo8 (): void = let
  fun foo9 (): void = let
    fun foo10 (): void = let
      val () = foo8 ()
      val () = foo9 ()
      val () = foo10 ()
    in
      foo8()
    end
  in
    foo8 ()
  end
in
  foo9 ()
end



fun foo11 (): int -<fun1> int = let
  fun foo12 (x: int): int = x
in
  foo12
end

fun foo13 (): int = let
  val y = 1
  fun foo14 (x: int): int = y + x
in
  foo14 (1)
end

// ===================

fun foo15 ():<fun1> void = let
  val x = 1
  fun foo16 (y: int): int = x + y
  val y = foo16 (3)
in
end

fun foo17 (f: int -<fun> int, x: int): int = let
  val y = f (x)
in
  y
end

fun foo18 (f: int -<cloref1> int, x: int): int = let
  val y = f (x)
in
  y
end

fun foo19 (): int -<cloref1> int = let
  fun foo20 .<>.(x: int):<cloref1> int = x
in
  foo20
end

fun foo21 (): int -<fun1> int = let
  val y = 3
  fun foo22 .<>.(x: int):<fun1> int = x
in
  foo22
end

implement main0 () = let
  val () = print ("Helloworld, ATS2.\n");
in
end


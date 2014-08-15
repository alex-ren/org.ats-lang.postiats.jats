#include "share/atspre_define.hats"
#include "share/atspre_staload.hats"

// // Call a function via name.
// fun foo1 (x: int):<fun1> int = let
//   val x1: int = 1 + 2
//   val x2 = foo1 (x)
// in
//   x1
// end
// 
// // todo: call a function via object.
// 
// // Call a closure via object.
// fun foo2 (f: int-<fun1> int): int = let
//   val x = 1
//   val y = f (x)
// in
//   y
// end
// 
// todo ===========================
// Call a closure via name.
// fun foo3 (x: int): int = let
//   val x = 1
//   fun foo4 (y: int): int = y + x
//   val y = foo4 (2)
// in
//   y
// end

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
    
   
////
fun foo3 (): int -<fun1> int = let
  fun foo4 (x: int): int = x
in
  foo4
end

//fun foo5 (): int = let
//  val y = 1
//  fun foo6 (x: int): int = y + x
//in
//  foo6 (1)
//end

// ===================

//fun foo2 ():<fun1> void = let
//  val x = 1
//  fun foo3 (y: int): int = x + y
//  val y = foo2 (3)
//  val () = foo1 ()
//in
//end

//fun foo3 (f: int -<fun> int, x: int): int = let
//  val y = f (x)
//  val () = foo1 ()
//in
//  y
//end
//
//fun foo4 (f: int -<cloref1> int, x: int): int = let
//  val y = f (x)
//in
//  y
//end
//
//fun foo5 (): int -<fun> int = let
//  fun foo6 .<>.(x: int):<fun> int = x
//in
//  foo6
//end
//
//fun foo7 (): int -<fun> int = let
//  val y = 3
//  fun foo8 .<>.(x: int):<fun> int = x + y
//in
//  foo8
//end

//implement main0 () = let
//  val () = print ("Helloworld");
//in
//end


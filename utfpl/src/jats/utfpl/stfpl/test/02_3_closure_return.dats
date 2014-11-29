
val y = 0

fun foo1 ():<cloref1> int = let

  val a = 3

  fun foo2 ():<cloref1> (() -<cloref1> int) = let
    val b = a
    
  in
    foo1
  end
 
  val x = y + 1
in
  x
end


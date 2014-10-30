
fun foo2 ():<fun1> void = ()
fun foo1 .<>.():<fun> void = let
  // val () = foo2 ()
in
end

fun foo3 .<>. 
  ( f: () -<fun1> void
  , g: () -<fun> void
  ): () -<fun> void = let
  // val () = f ()
in
  g
end

fun foo6 (): void = let
  fun foo4 (f: () -<fun1> int): void = ()
  val x = 3 + 4
  fun foo5 (): int = let
    val y = x + 4
  in y end
  val () = foo4 (foo5)
in
end


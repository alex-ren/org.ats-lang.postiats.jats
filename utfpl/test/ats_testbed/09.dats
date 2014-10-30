
fun foo (x: int):<fun1> int = let
  val x = 99 + 100
  fun fclo1 (): int = let
    val y = 33 + x
  in
    fclo2 ()
  end
  and fclo2 (): int = let
    val y = 55 + x
  in
    fclo1 ()
  end
in
  44
end




fun foo (): int = let
  val x = if 1 > 2 then 3
          else 4
in
  x
end

fun foo1 (): void = let
  val x = if 1 > 2 then 
             if 3 > 4 then 5 else 6 
          else 4
in
  x
end
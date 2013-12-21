
fun xx (): int = 3

fun foo (): int = let
  val x = if 1 > 2 then xx ()
          else 4
in
  x
end

fun foo1 (): void = let
  val x = if 1 > 2 then 
             if 3 > 4 then 5 else xx ()
          else 4
in
  x
end

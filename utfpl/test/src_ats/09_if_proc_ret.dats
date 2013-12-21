
fun xx (): int = 3

fun foo (): int = let
in
  if 1 > 2 then xx ()
  else 4
end

fun foo2 (): int = let
in
  if 1 > 2 then
    if 3 > 4 then 5 else xx ()
  else 4
end
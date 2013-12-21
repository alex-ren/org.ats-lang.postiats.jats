
fun foo (): int = let
in
  if 1 > 2 then 3
  else 4
end

fun foo2 (): int = let
in
  if 1 > 2 then
    if 3 > 4 then 5 else 6 
  else 4
end
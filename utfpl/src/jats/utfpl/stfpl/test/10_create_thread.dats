
staload "./conats.sats"


fun foo (x: int): int = 
  if x <= 1 then 1
  else x * foo (x - 1)




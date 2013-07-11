
staload "prelude/DATS/integer.dats"

fun init (x: int, y: int): int = x + y

val a = init (3, 4)
val () = println! ("hello global", a)

fun foo_bt_var (x: &'(int, char)): int = let
  val () = x := '(a, 'c')
in
  if x.0 = 'c' then 4 else 3
end

implement main0 () = let
 val () = assertloc (a = 7)
 val b = a + 3
 val () = println! ("hello world") 
in
end

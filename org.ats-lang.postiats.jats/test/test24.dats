
staload "prelude/DATS/integer.dats"

fun init (x: int, y: int): int = x + y

val a = init (3, 4)

fun foo_bt_var (x: &'(int, char)): void = let
  val () = x := '(a, 'c')
in
end

implement main0 () = let
in end

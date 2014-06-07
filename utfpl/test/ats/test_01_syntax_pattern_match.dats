
staload "libats/ML/SATS/basis.sats"

fun foo (): void = let
  val x = list0_nil ()
in
  case- x of
  | list0_cons (x: int, list0_cons (y, list0_nil ()): list0 int) => ()
end


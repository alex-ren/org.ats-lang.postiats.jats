

staload "prelude/DATS/integer.dats"

typedef tuple_box = '(int, int)
typedef tuple_flat = @(int, int)


fun foo_bt_val (x: tuple_box): void = ()
fun foo_ft_val (x: tuple_flat): void = ()

fun foo_bt_var (x: &tuple_box): void = let
  val () = x := '(52, 62)
  // val () = x.0 := 52  // error
in
end

fun foo_ft_var (x: &tuple_flat): void = let
  val () = x := @(71, 82)
  val () = x.0 := 72  // error
in
end

implement main0 () = let
  val x_bt_val = '(1, 2): tuple_box
  val x_ft_val = @(3, 4): tuple_flat

  var x_bt_var = '(5, 6): tuple_box
  var x_ft_var = @(7, 8): tuple_flat

// =====================

  val () = x_bt_var := '(51, 61)
  val () = assertloc (x_bt_var.0 = 51)
  // val () = x_bt_var.0 := 3  // error

  val () = foo_bt_var (x_bt_var)
  val () = assertloc (x_bt_var.0 = 52)

// =====================

  val () = x_ft_var.0 := 71
  val () = assertloc (x_ft_var.0 = 71)
  val () = x_ft_var := @(71, 81)
  val () = assertloc (x_ft_var.1 = 81)

  val () = foo_ft_var (x_ft_var)
  val () = assertloc (x_ft_var.0 = 72)
  val () = assertloc (x_ft_var.1 = 82)

// =====================

  val x_str0_val = "abc": string0
  var x_str0_var = "def"

  val () = println! x_str0_val
  val () = println! x_str0_var

  // val x_list_val = list_cons ('a', list_cons ('b', list_nil)) // not supported


in
end




staload "prelude/DATS/integer.dats"


fun foo_bt_val (x: '(int, int)): void = let
  val () = println! ("int is ", x.0)
in
end

fun foo_ft_val (x: @(int, double)): void = let
  val () = println! ("int is ", x.0)
in
end

fun foo_bt_var (x: &'(int, char)): void = let
  val () = x := '(52, 'c')
  // val () = x.0 := 52  // error
in
end

fun foo_ft_var (x: & @(int, string)): void = let
  val () = x := @(72, "cde")
  val () = x.1 := "def"
in
end

fun foo_int (): int = 3

implement main0 () = let
 
  val x = foo_int ()
  val x = x + 1
  
  var x = foo_int ()
  var x = x + 1
  val () = x := x + 2
  
  val x_bt_val = '(0, 1)
  val x_bt_val = '(1, 2)
  val () = foo_bt_val x_bt_val

// =====================

  val x_ft_val = @(2, 0.1)
  val x_ft_val = @(3, 1.2)
  val () = foo_ft_val x_ft_val

// =====================
 
  var x_bt_var = '(5, 'a')
  val () = x_bt_var := '(51, 'b')
  val () = assertloc (x_bt_var.0 = 51)
  // val () = x_bt_var.0 := 3  // error

  val () = foo_bt_var (x_bt_var)
  val () = assertloc (x_bt_var.0 = 52)

// =====================
  var x_ft_var = @(7, "abc"): (int, string)
  val () = x_ft_var.0 := 70
  val () = assertloc (x_ft_var.0 = 70)
 
   val () = x_ft_var := (71, "bcd")
   val () = assertloc (x_ft_var.1 = "bcd")
// 
   val () = foo_ft_var (x_ft_var)
   val () = assertloc (x_ft_var.0 = 72)
   val () = assertloc (x_ft_var.1 = "def")
   val () = println! ("done")


  // val x_list_val = list_cons ('a', list_cons ('b', list_nil)) // not supported

in
end


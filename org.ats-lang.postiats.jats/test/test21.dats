
staload "prelude/DATS/integer.dats"




implement main0 () = let
  val xx = (5, "abc")
  val () = println! ("ans = ", xx.1)
  val () = assertloc (xx.0 = 5)
in
end
 

// =====================


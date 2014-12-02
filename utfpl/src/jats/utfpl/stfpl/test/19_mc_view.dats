
staload "./conats.sats"

fun foo (): void = let
  prval mc_v = mc_vlockview_get (0, 0, 1, 1)
  prval mc_v2 = mc_vlockview_get (0, 0, 1, 2)
  prval () = mc_vlockview_put (mc_v)
  prval () = mc_vlockview_put (mc_v2)
in
end

val () = foo ()


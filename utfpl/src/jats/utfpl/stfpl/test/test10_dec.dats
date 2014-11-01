
extern val x: int and y: int

val x = 3

prval y = 4

val x1 = lam () =<cloref1> 3: int
// and y = lam () =<cloref1> (y ())

extern fun foo (): void 
and goo (): void


fun foo1 (): void = let
  extern fun foo2 (): void
in
end


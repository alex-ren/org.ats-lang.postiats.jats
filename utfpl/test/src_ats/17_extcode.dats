
val x = 3

fun foo () = 3

%{$

#assert main deadlockfree;

#define Prop1 (n_40 < 2);

#assert main |= G Prop1;
%}


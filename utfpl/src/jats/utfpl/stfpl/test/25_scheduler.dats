
staload "./conats.sats"

fun foo2 (x: int, y: int): int = y

fun foo {a:t@ype} (xs: list a): void = let
  val x = list_is_nil (xs)
  val y = list_get_header (xs)
  val x = foo2 (2, 3)
in
end



%{$

// id of currrent running thread
var cur_tid = 0;

// list of runable threads
var runable;
var runable_size;


runable_get_random(xs, n) = 
  if (n == 0) {Skip}
  else if (n == 1) {
    run_first_one(xs)
  }
  else {
    run_first_one(xs)
    <>
    runable_get_random(sys_mylib_obj.list_get_tail(xs), n - 1)
  };
  
run_first_one(xs) = 
  {cur_tid = sys_mylib_obj.list_get_header(xs);
  } -> 
  scheduler_random
  ;

scheduler_random = [cur_tid == -1] runable_get_random(runable, runable_size);


var gx = 0;

P(sid) = {
    gx = 1;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sid] {  // schedule in
    gx = 2;
  } -> 
  P2(sid); 
  { var x = 5;
  } -> 
  Skip;
  
P2(sid) = {
    gx = 3;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sid] {  // schedule in
    gx = 4;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sid] // schedule in
  Skip;

Main2 = {
  var xs = sys_mylib_obj.list_nil ();
  xs = sys_mylib_obj.list_cons (0, xs);
  runable = xs;
  runable_size = 1;
  cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == 0] P(0);  // schedule in
  {
    // finalize thread
    // remove tid
    runable = sys_mylib_obj.list_remove_element(runable, 0);
    runable_size = sys_mylib_obj.list_length(runable);
    
    
    cur_tid = -1;  // schedule out
  } -> Skip
  ;
  
main2 = Main2 ||| scheduler_random;
  
#assert main2 deadlockfree;

// #assert main |= G sys_assertion;


%}


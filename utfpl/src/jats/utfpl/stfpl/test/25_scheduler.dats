
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
  ifa (n == 0) {Skip}
  else ifa (n == 1) {
    run_first_one(xs)
  }
  else {
    run_first_one(xs)
    <>
    runable_get_random(sys_mylib_obj.list_get_tail(xs), n - 1)
  };
  
run_first_one(xs) = 
  run_first_one_s1{cur_tid = sys_mylib_obj.list_get_header(xs);
  } -> 
  scheduler_random
  ;

var new_tid = -1;  // indicator for creating new thread
var new_fn;
var new_args;

scheduler_random = [cur_tid == -1](
  ifa (new_tid == -1) {  // schedule
    runable_get_random(runable, runable_size)
  } else {  // create new thread
    sch_thread_starter2(new_tid, new_fn, new_args)
  });
  
sch_thread_starter2(tid, fn, args) = sch_thread_starter2_s1{
    new_tid = -1;
    runable =  sys_mylib_obj.list_cons(tid, runable);
    runable_size = runable_size + 1;
  } -> ifa (fn == 1) {
    ([cur_tid == tid]P(tid);thread_finalize2(tid)) ||| scheduler_random
  } else ifa (fn == 2) {
    ([cur_tid == tid]P2(tid);thread_finalize2(tid)) ||| scheduler_random
  } else {
    Stop
  };
  
thread_finalize2(sys_tid) = thread_finalize2_s1.sys_tid{
    // finalize thread
    // remove tid
    runable = sys_mylib_obj.list_remove_element(runable, sys_tid);
    runable_size = runable_size - 1;
    cur_tid = -1;  // schedule out
  } -> Skip;

thread_create(sys_tid, tid, fn, args) = thread_create_s1.sys_tid{
    new_tid = tid;
    new_fn = fn;
    new_args = args;
  
    cur_tid = -1;
  } -> Skip;
  [cur_tid == sys_tid] Skip;
   

var gx = 0;

P(sid) = p_s1.sid{
    gx = 1;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sid] p_s2.sid{  // schedule in
    gx = 2;
  } -> 
  P2(sid); 
  p_s2.sid{ var x = 5;
  } -> 
  Skip;
  
P2(sid) = p2_s1.sid{
    gx = 3;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sid] p2_s2.sid{  // schedule in
    gx = 4;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sid] // schedule in
  Skip;

Main2 = main_s1.0{
  var xs = sys_mylib_obj.list_nil ();
  xs = sys_mylib_obj.list_cons (0, xs);
  runable = xs;
  runable_size = 1;
  cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == 0] P(0);  // schedule in
  thread_create(0, 1, 1, 0);
  thread_create(0, 2, 2, 0);
  thread_finalize2(0);

  
main2 = Main2 ||| scheduler_random;
  
#assert main2 deadlockfree;

// #assert main |= G sys_assertion;


%}


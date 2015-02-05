
staload "./conats.sats"

fun foo2 (x: int, y: int): int = y

fun foo {a:t@ype} (xs: list a): void = let
  val x = list_is_nil (xs)
  val y = list_get_header (xs)
  val x = foo2 (2, 3)
in
end



%{$
// =================================
// Set the initial capacity to 10.

var cond2 = [0(COND_NO)];
var<AtomRefManager> cond_sleep_list_manager = new AtomRefManager(COND_NO);

InitCond2(n) = InitCond_s1{
  cond_sleep_list_manager.initialize(n, sys_mylib_obj.list_nil());
} -> Skip;


CondSignal(sys_tid, c) = 
  ifa(sys_mylib_obj.list_is_nil(cond_sleep_list_manager.getElement(c))) {
    CondSignal_empty.sys_tid -> Skip
  } else {
    CondSignalRandom(sys_tid, c, cond_sleep_list_manager.getElement(c))
  }

CondSignalRandom(sys_tid, c, sleep_list) = 
  ifa (sys_mylib_obj.list_is_nil(sys_mylib_obj.list_get_tail(sleep_list))) {
    CondSignalRandom_first(sys_tid, c, sys_mylib_obj.list_get_header(sleep_list))
  } else {
    CondSignalRandom_first(sys_tid, c, sys_mylib_obj.list_get_header(sleep_list))
    <>
    CondSignalRandom(sys_tid, c, sys_mylib_obj.list_get_tail(sleep_list))
  }
  ;
  
CondSignalRandom_first(sys_tid, c, tid) = CondSignalRandom_first_s1.sys_tid{
  // remove from sleep list
  var sleep_list = cond_sleep_list_manager.getElement(c);
  var new_list = sys_mylib_obj.list_remove_element(sleep_list, tid);
  cond_sleep_list_manager.setElement(c, new_list);
  
  // add to runable
  runable = sys_mylib_obj.list_cons(tid, runable);
  runable_size = runable_size + 1;
  } -> Skip;


CondBroadcast(sys_tid, c) = CondBroadcast_s1{
  var sleep_list = cond_sleep_list_manager.getElement(c);
  runable = sys_mylib_obj.list_revappend(runable, sleep_list);
  runable_size = runable_size + sys_mylib_obj.list_length(sleep_list);
  cond_sleep_list_manager.setElement(c, sys_mylib_obj.list_nil());
  } -> Skip;

CondWait(sys_tid, c, m) = CondWait_st.sys_tid{
  // remove from runable
  runable = sys_mylib_obj.list_remove_element(runable, sys_tid);
  runable_size = runable_size - 1;
  // add to sleep
  var new_list = sys_mylib_obj.list_cons(sys_tid, cond_sleep_list_manager.getElement(c));
  cond_sleep_list_manager.setElement(c, new_list);
  } -> Skip;
  // release mutex. This would cause schedule out
  MutexRelease(sys_tid, m);
  MutexAcquire(sys_tid, m);



// =================================

// Set the initial capacity to 10.

var mutex2 = [-1(MUTEX_NO)];
var<AtomRefManager> mutex_waiting_list_manager = new AtomRefManager(MUTEX_NO);

InitMutex2(n) = InitMutex_s1{
  mutex_waiting_list_manager.initialize(n, sys_mylib_obj.list_nil());
} -> Skip;

MutexAcquire(sys_tid, m) =
  ifa (mutex2[m] == -1) {  // can have the mutex
    MutexAcquire_yes.sys_tid{mutex2[m] = sys_tid;} -> Skip
  } else {
    MutexAcquire_no.sys_tid{  // cannot have the mutex
      // remove current thread from runable
      runable = sys_mylib_obj.list_remove_element(runable, sys_tid);
      runable_size = runable_size - 1;
      
      // add current thread to waiting list
      var new_list = sys_mylib_obj.list_cons(sys_tid, mutex_waiting_list_manager.getElement(m));
      mutex_waiting_list_manager.setElement(m, new_list);
      
      cur_tid = -1;  // schedule out
    } -> ([cur_tid == sys_tid] Skip)  // schedule in
  }
  ;

MutexRelease(sys_tid, m) = 
  ifa(sys_mylib_obj.list_is_nil(mutex_waiting_list_manager.getElement(m))) {
    MutexRelease_s1.sys_tid{
      mutex2[m] = -1;
      cur_tid = -1;  // schedule out
    } -> ([sys_tid == cur_tid] Skip)  // schedule in
  } else {
    MutexReleaseRandom(sys_tid, m, mutex_waiting_list_manager.getElement(m))
  }

MutexReleaseRandom(sys_tid, m, waiting_list) = 
  ifa (sys_mylib_obj.list_is_nil(sys_mylib_obj.list_get_tail(waiting_list))) {
    MutexReleaseRandom_first(sys_tid, m, sys_mylib_obj.list_get_header(waiting_list))
  } else {
    MutexReleaseRandom_first(sys_tid, m, sys_mylib_obj.list_get_header(waiting_list))
    <>
    MutexReleaseRandom(sys_tid, m, sys_mylib_obj.list_get_tail(waiting_list))
  }
  ;
  
MutexReleaseRandom_first(sys_tid, m, tid) = {
  // remove from waiting list
  var waiting_list = mutex_waiting_list_manager.getElement(m);
  var new_list = sys_mylib_obj.list_remove_element(waiting_list, tid);
  mutex_waiting_list_manager.setElement(m, new_list);
  
  mutex2[m] = tid;
  
  // add to runable
  runable = sys_mylib_obj.list_cons(tid, runable);
  runable_size = runable_size + 1;
  
  cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sys_tid] Skip;  // Schedule in
  
// =================================
// Scheduler

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
    ([cur_tid == tid]P1(tid, args);thread_finalize2(tid)) ||| scheduler_random
  } else ifa (fn == 2) {
    ([cur_tid == tid]P2(tid, args);thread_finalize2(tid)) ||| scheduler_random
  } else ifa (fn == 3) {
    ([cur_tid == tid]P3(tid, args);thread_finalize2(tid)) ||| scheduler_random
  } else ifa (fn == 4) {
    ([cur_tid == tid]P4(tid, args);thread_finalize2(tid)) ||| scheduler_random
  }
  else {
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
  } -> ([cur_tid == sys_tid] Skip);

// ===================================

var gx = 0;

P1(sys_tid, args) = p1_s1.sys_tid{
    gx = 1;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sys_tid] p1_s2.sys_tid{  // schedule in
    gx = 2;
  } -> 
  MutexAcquire(sys_tid, args);
  MutexRelease(sys_tid, args);
  p1_s3.sys_tid{ var x = 5;
  } -> 
  Skip;
  
P2(sys_tid, args) = p2_s1.sys_tid{
    gx = 3;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sys_tid] p2_s2.sys_tid{  // schedule in
    gx = 4;
    cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == sys_tid] Skip;// schedule in
  MutexAcquire(sys_tid, args);
  MutexRelease(sys_tid, args);

var P3_x = 0;
P3(sys_tid, args) = p3_s1.sys_tid{
    gx = 1;
  } -> Skip;
  MutexAcquire(sys_tid, args);
  {P3_x = 1;} ->
  CondWait(sys_tid, args, args);
  MutexRelease(sys_tid, args);

P4(sys_tid, args) = p4_s1.sys_tid{
    gx = 2;
  } -> Skip;
  MutexAcquire(sys_tid, args);
  CondSignal(sys_tid, args);
  MutexRelease(sys_tid, args);

Main2 = 
  InitMutex2(MUTEX_NO);  // initialize mutex
  InitCond2(COND_NO);
  main_s1.0{
  var xs = sys_mylib_obj.list_nil ();
  xs = sys_mylib_obj.list_cons (0, xs);
  runable = xs;
  runable_size = 1;
  cur_tid = -1;  // schedule out
  } -> Skip;
  [cur_tid == 0] Skip;  // schedule in
  // sys_tid, tid, fn, args
  // thread_create(0, 1, 1, 0);
  // thread_create(0, 2, 2, 0);
  // thread_create(0, 3, 2, 0);
  
  thread_create(0, 4, 3, 0);
  Main2_loop(0);
  thread_create(0, 5, 4, 0);
  thread_finalize2(0);  
  
  
Main2_loop(sys_tid) = ifa (P3_x == 1) {
  Skip
  } else {
    {cur_tid = -1;} -> ([cur_tid == sys_tid] Main2_loop(sys_tid))
  }
  ;
  
main2 = Main2 ||| scheduler_random;
  
#assert main2 deadlockfree;

// #assert main |= G sys_assertion;

%}


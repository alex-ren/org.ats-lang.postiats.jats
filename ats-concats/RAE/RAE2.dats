
(* ************* ************** *)

abstype pid_t

(* ************* ************** *)

datatype list0 (a: t@ype+) =
| list0_nil (a)
| list0_cons (a) of (a, list0 a)

#define :: list0_cons
#define nil list0_nil

dataview option_v (a: view, b: bool) =
| option_v_none (a, false)
| option_v_some (a, true) of a

(* ************* ************** *)

(*
* Always bound a pid to a name.
* The return value indicated whether the name has been used.
*)
extern fun register_pid (name: string, pid_t): bool  // make sure this won't fail by reasoning out of the program
extern fun get_pid (name: string): pid_t  // blocked if name hasn't been registered
extern fun get_self_pid (): pid_t  // this won't fail
extern fun spawn (proc: () -> void): pid_t  // make sure this won't fail by reasoning out of the program

(* ************* ************** *)

sortdef prop_s = int

abstype prop_t (prop_s)
typedef prop_t = [p: prop_s] prop_t (p)

abstype prop_type_t
abstype prop_value_t

extern fun prop_get_type (prop: prop_t): prop_type_t
 
sortdef table_s = int
abstype table_t (table_s)
typedef table_t = [t: table_s] table_t (t)

abstype entry_t

absview to_achieved

(* ************* ************** *)

(*
* ret < 0: conflict
* ret == 0: already achieved
* ret > 0: seq. in the waiting queue
*)
extern fun table_register_prop (t: table_t, pid: pid_t, prop: prop_t): 
  [ret: int] (option_v (to_achieved, ret > 0) | int ret, table_t)

extern fun table_create (): table_t

// Get a property from the table based on the type of the property
// Need a proof that the property is in the table
extern fun table_get_prop (vpf: to_achieved | t: table_t, pt: prop_type_t): prop_t

// return value (tasks, action)
// action: 0 -> inform task to start executing
//         1 -> inform task to stop
extern fun table_update_prop (t: table_t, prop: prop_t): (list0 pid_t, int)

// contact appropriate devices to achieve the required property
extern fun try_achieve_prop (prop: prop_t): void

(* ************* ************** *)

(* interface for sending message *)

(*
* assume: Send won't fail, message won't be lost.
*)

datatype send_operation_t =
| so_mgr_sub
| so_mgr_achieve
| so_mgr_release
| so_mgr_update
| so_task_sub_reply
| so_task_achieve_reply
| so_task_prop_invalid

dataviewtype recv_msg_t =
| {p: prop_s} rv_mgr_sub of (pid_t, prop_t p)
| rv_mgr_achieve of (to_achieved | pid_t, prop_type_t)
| rv_mgr_release of (pid_t)
| rv_mgr_update of (prop_t)
| {i:int} rv_task_sub_reply of (option_v (to_achieved, i > 0) | int i)
| rv_task_achieve_reply
| rv_task_prop_invalid of (pid_t)

abstype msg_t

extern fun select (mask: list0 send_operation_t): recv_msg_t

(* interface for manager *)
extern fun send_mgr_sub {p: prop_s} (target: pid_t, pid: pid_t, prop: prop_t p): void

extern fun send_mgr_achieve (vpf: to_achieved | 
  target: pid_t, pid: pid_t, prop: prop_type_t): void

extern fun send_mgr_release (target: pid_t, pid: pid_t): void

extern fun send_mgr_update (target: pid_t, prop: prop_t): void

(* ======= ======= *)

(* interface for task *)
(*
* ret < 0: conflict, cannot go
* ret == 0: already achieved, good to go
* ret > 0: seq. in the waiting queue, starting from 1
*)
extern fun send_task_sub_reply {i: int} (
    pf: option_v (to_achieved, i > 0) | target: pid_t, ret: int i): void

extern fun send_task_achieve_reply (target: pid_t): void

extern fun urgent_send_task_prop_invalid (target: pid_t, src: pid_t): void

(* ************* ************** *)

extern fun submit_task {p:prop_s} (mgr: pid_t, proc: pid_t, prop: prop_t p): 
  [ret: int] (option_v (to_achieved, ret > 0) | int ret)

extern fun achieve_prop (vpf: to_achieved 
                         | mgr: pid_t, pid: pid_t, prop_type: prop_type_t): void

extern fun run_task (): int

fun task (prop: prop_t): int = let
  val mgr = get_pid ("manager")
  val self = get_self_pid ()
  val (opf | ret) = submit_task (mgr, self, prop)
in
  if ret < 0 then let prval option_v_none () = opf in ~1 end
  else let
    val () = if (ret > 0) then let
      prval option_v_some (vpf) = opf
      val prop_type = prop_get_type (prop)
    in
      achieve_prop (vpf | mgr, self, prop_type)
    end else let prval option_v_none () = opf in end
    val ret = run_task () 
//      until_urgent_receive
//      | urgent_send_task_prop_invalid () => ~1
    val () = send_mgr_release (mgr, self)
  in
    ret
  end
end

implement achieve_prop (vpf | mgr, pid, prop_type) = let
  val ret = send_mgr_achieve (vpf | mgr, pid, prop_type)
  val mask = list0_cons (so_task_achieve_reply, list0_nil ())
  val msg = select (mask)
in
  case- msg of
  | ~rv_task_achieve_reply () => ()
end

implement submit_task (mgr, self, prop) = 
let
  val () = send_mgr_sub (mgr, self, prop)  // no return value, 
  val mask = list0_cons (so_task_sub_reply, list0_nil ())
  val kind = select (mask)
in
  case- kind of
  | ~rv_task_sub_reply (opf | ret) => (opf | ret)
end

(* ************* ************** *)

extern fun manager {t: table_s} (t: table_t t): void

fun manager_init (): void = let
  val t = table_create ()
  val _ = register_pid ("manager", get_self_pid ())
in
  manager (t)
end
  
implement manager {t} (t) = let
  val mask = so_mgr_sub :: so_mgr_achieve :: so_mgr_update :: nil
  val msg = select (mask)
in
case- msg of
// message from task
| ~rv_mgr_sub (pid, prop) => let
  val (opt_v | ret, t') = table_register_prop (t, pid, prop)
  val () = send_task_sub_reply (opt_v | pid, ret)
in
  manager (t')
end
// message from task
| ~rv_mgr_achieve (vpf | pid, prop_type) => let
  val target_prop = table_get_prop (vpf | t, prop_type)
  val () = try_achieve_prop (target_prop)
in
  manager (t)
end
// message from devices
| ~rv_mgr_update (prop) => let
  val (tasks, action) = table_update_prop (t, prop)
in
  if action = 0 then let
    fun loop (tasks: list0 pid_t): void =
      case+ tasks of
      | list0_cons (pid, tasks) => let
        val () = send_task_achieve_reply (pid)
      in
        loop (tasks)
      end
      | list0_nil () => ()
  in
    loop (tasks)
  end
  else let
    fun loop (tasks: list0 pid_t): void =
      case+ tasks of
      | list0_cons (pid, tasks) => let
        val () = urgent_send_task_prop_invalid (pid, get_self_pid ())
      in
        loop (tasks)
      end
      | list0_nil () => ()
  in
    loop (tasks)
  end
end
end // end of [case- msg]

(* ************* ************** *)

implement main0 () = let
  val pid_mgr = spawn (manager_init)
// todo  val pid_task1 = spawn (task)
// todo  val pid_task2 = spawn (task)
in
end




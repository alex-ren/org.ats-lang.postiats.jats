

(* ************* ************** *)

abstype pid_t

extern fun pid_eq (x: pid_t, y: pid_t):<0> bool
overload = with pid_eq

(* ************* ************** *)

#define :: cons

(* ************* ************** *)

abstype user_db_t

extern fun user_db_create (): user_db_t

extern fun user_db_find_by_name (udb: user_db_t, name: string): Option (pid_t)
extern fun user_db_find_by_id (udb: user_db_t, id: pid_t): Option (string)

// Assume user with name "name" is not in the db yet.
extern fun user_db_add_user (udb: user_db_t, id: pid_t, name: string): user_db_t


// Returning true means user with "id" is already in the db so that it can be removed.
extern fun user_db_remove_user (udb: user_db_t, id: pid_t): (bool, user_db_t)

(* ************* ************** *)

(*
* Always bound a pid to a name.
* The return value indicated whether the name has been used.
*)
// make sure this won't fail by reasoning out of the program
extern fun register_pid (name: string, pid: pid_t): bool

// blocked if name hasn't been registered
extern fun get_pid (name: string): pid_t

extern fun get_self_pid (): pid_t  // this won't fail

extern fun spawn (proc: () -<cloref1> void): pid_t  // make sure this won't fail by reasoning out of the program

abstype msg_t

extern fun select (mask: List0 int): (int, msg_t)

// Will this fail? Assume not.
// t: int: tag of the msg
symintr send_via_pid
extern fun send_via_pid_arg0 {p:int} {a:type} (to: pid_t, port: int p): void
extern fun send_via_pid_arg1 {p:int} {a:viewtype} (to: pid_t, port: int p, msg: a): void
overload send_via_pid with send_via_pid_arg0
overload send_via_pid with send_via_pid_arg1

(* ************ ************* *)


abst@ype typeidof (a:t@ype) = int
typedef typeid = [a: t@ype] (typeidof a)
extern fun typeid_create {a:t@ype} (): typeidof (a)
 

absview selected (a:t@ype)

symintr tselect
extern fun tselect_list (mask: List0 typeid): [a:t@ype] (selected a | typeidof a)

extern fun tselect2 {a,b:t@ype} (x: typeidof a, y: typeidof b): 
  [a:t@ype] (selected a | typeidof a)

overload tselect with tselect_list
overload tselect with tselect2
 
dataprop ty_eq (a:t@ype, b:t@ype) = 
| {a:t@ype} eq (a, a)
 
dataprop optionprop (a:prop, b: bool) =
| optionprop_nil (a, false) of ()
| optionprop_some (a, true) of a

extern fun {ta,tb:t@ype} typeid_eq (
  x: typeidof (ta), y: typeidof (tb)): 
  [b:bool] (optionprop (ty_eq (ta, tb), b) | bool b)

symintr tsend_pid
extern fun tsend_pid1 {p:int} {a:t@ype} (to: pid_t, x: typeidof (a), a): void
overload tsend_pid with tsend_pid1

symintr trecv

extern fun trecv0 {a:t@ype} (pf: selected a): a
extern fun trecv1 {a:t@ype} (x: typeidof a): a
extern fun trecv2 {a,b,c:t@ype} 
  (x: typeidof a, fx: a -> c, y: typeidof b, fy: b -> c): c

overload trecv with trecv0
overload trecv with trecv1
overload trecv with trecv2


(* ************* ************** *)

datatype req_to_server_t =
| logon of (pid_t, string)
| logoff of (pid_t)
| msg_to of (pid_t (*from*), string (*to*), string (*msg*))

datatype reply_t =
| stop
| logged_on
| logged_off
| receiver_not_found
| sent

datatype req_to_client_t =
| logoff
| msg_to of (string (*to*), string (*msg*))

extern val int_id: typeidof (int)
extern val intp_id: typeidof ('(int))
extern val req_to_server_t_id: typeidof (req_to_server_t)
extern val response_type_id: typeidof ('(int, string))
 
(* ************* ************** *)

// To server
// 0: (req: req_to_server_t)

// To client
// 0: reply_from_messenger: (rep: reply_t, err: Option string)
// 1: req_from_user: (req: req_to_client_t)
// 2: info_from_messenger (from: string, msg: string)

// 


typedef users_t = user_db_t

extern fun server_logon (from: pid_t, name: string, udb: users_t): users_t
extern fun server_logoff (from: pid_t, udb: users_t): users_t
extern fun server_transfer (from: pid_t, to: string, msg: string, udb: users_t): void
extern fun server_transfer_do (from: pid_t, name: string, 
                               to: string, msg: string, udb: users_t): void

// extern fun{x:t0p} list_deletefirst$pred (x): bool
// extern fun{x:t0p} list_deletefirst {n:int} (xs: list (INV(x), n)): (bool, listLte (x, n))

(* ************* ************** *)

fun server (udb: users_t): void = let
  val req = trecv (req_to_server_t_id)
in
  case+ req of
  | logon (from, name) => let
    val udb' = server_logon (from, name, udb)
  in
    server (udb')
  end
  | logoff (from) => let
    val udb' = server_logoff (from, udb)
  in
    server (udb')
  end
  | msg_to (from, to, msg) => let
    val () = server_transfer(from, to, msg, udb)
  in
    server (udb)
  end
end

fun start_server (): void = let
  fun server_proc ():<cloref1> void = server (user_db_create ())
  val pid = spawn(server_proc)
  val _ = register_pid ("messenger", pid)
in  end

implement server_logon (from, name, udb) = let
  val opt = user_db_find_by_name (udb, name)
in
  if option_is_some (opt) then let
    val () = tsend_pid (from, response_type_id, '(3, "user_exists_at_other_node"))
  in
    udb
  end
  else let
    val () = tsend_pid (from, int_id, 4)
    val udb' = user_db_add_user (udb, from, name)
  in
    udb'
  end
end

implement server_logoff (from, udb) = let
  val (_, udb') = user_db_remove_user (udb, from)
in
  udb'
end

implement server_transfer (from, to, msg, udb) = let
  val opt = user_db_find_by_id (udb, from)
in
  case+ opt of
  | Some (name) =>
    server_transfer_do (from, name, to, msg, udb)
  | None () => let
    val () = send_via_pid (from, 3, "you_are_not_logged_on")
  in
    ()
  end
end
 
implement server_transfer_do (from, name, to, msg, udb) = let
  val opt_id = user_db_find_by_name (udb, to)
in
  case+ opt_id of
  | Some (pid) => let
    val () = send_via_pid (pid, 2, '(name, msg))
    val () = send_via_pid (from, 0, '(sent (), None()))
  in end
  | None () => send_via_pid (from, 0, '(receiver_not_found (), None ()))
end


////
fun start_client (name: string): bool = let
  fun client_proc (
  


  
















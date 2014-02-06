

(* ************* ************** *)

abstype pid_t

extern fun pid_eq (x: pid_t, y: pid_t):<0> bool
overload = with pid_eq

(* ************* ************** *)

#define :: cons

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
// t: int: tag of the message
symintr send_via_pid
extern fun send_via_pid_arg0 {p:int} {a:type} (to: pid_t, port: int p): void
extern fun send_via_pid_arg1 {p:int} {a:type} (to: pid_t, port: int p, msg: a): void
overload send_via_pid with send_via_pid_arg0
overload send_via_pid with send_via_pid_arg1


(* ************* ************** *)

// 0: logon; client to server
// 1: logoff; client to server
// 2: message_to; client to server

// 3: messenger, stop; server to client
// 4: messenger logged_on; server to client

// 


typedef users_t = List0 '(pid_t, string)

extern fun server_logon (from: pid_t, name: string, user_list: users_t): users_t
extern fun server_logoff (from: pid_t, user_list: users_t): users_t
extern fun server_transfer (from: pid_t, to: string, message: string, user_list: users_t): void
extern fun server_transfer_do (from: pid_t, name: string, 
                               to: string, message: string, user_list: users_t): void

extern fun{x:t0p} list_deletefirst$pred (x): bool
extern fun{x:t0p} list_deletefirst {n:int} (xs: list (INV(x), n)): (bool, listLte (x, n))

(* ************* ************** *)

fun server (user_list: users_t): void = let
  val mask = 0 :: 1 :: 2 :: nil
  val (port, msg) = select (mask)
in
  // logon
  case- port of
  | _ when port = 0 => let
    extern castfn to_logon (msg: msg_t): (pid_t, string)
    val (from, name) = to_logon (msg)
    val new_user_list = server_logon (from, name, user_list)
  in
    server (new_user_list)
  end
  | _ when port = 1 => let
    extern castfn to_logoff (msg: msg_t): pid_t
    val from = to_logoff (msg)
    val new_user_list = server_logoff (from, user_list)
  in
    server (new_user_list)
  end
  | _ when port = 2 => let
    extern castfn to_message_to (msg: msg_t): (pid_t (*from*), string, string (*to*))
    val (from, message, to) = to_message_to (msg)
    val () = server_transfer(from, to, message, user_list)
  in
    server (user_list)
  end
end

fun start_server (): void = let
  fun server_proc ():<cloref1> void = server (nil)
  val pid = spawn(server_proc)
  val _ = register_pid ("messenger", pid)
in  end

implement server_logon (from, name, user_list) = let
  implement list_find$pred<'(pid_t, string)> (x) = x.1 = name
  val fret = list_find_opt (user_list)
in
  if option_vt_is_some (fret) then let
    val _ = option_vt_unsome (fret)
    val () = send_via_pid (from, 3, "user_exists_at_other_node")
  in
    user_list
  end
  else let
    val () = option_vt_unnone (fret)
    val () = send_via_pid (from, 4)
    val new_user_list = '(from, name) :: user_list
  in
    new_user_list
  end
end

implement server_logoff (from, user_list) = let
  implement list_deletefirst$pred<'(pid_t, string)> (x) = x.0 = from
  val (check, new_users) = list_deletefirst (user_list)
in
  new_users
end

implement server_transfer (from, to, message, user_list) = let
  implement list_find$pred<'(pid_t, string)> (x) = x.0 = from
  val fret = list_find_opt (user_list)
in
  if option_vt_is_some (fret) then let
    val '(_, name) = option_vt_unsome (fret)
  in
    server_transfer_do (from, name, to, message, user_list)
  end
  else let
    val () = option_vt_unnone (fret)
    val () = send_via_pid (from, 3, "you_are_not_logged_on")
  in
    ()
  end
end
 


  

















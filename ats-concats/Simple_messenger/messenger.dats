

(* ************* ************** *)

abstype pid_t

(* ************* ************** *)

datatype list0 (a: t@ype+) =
| list0_nil (a)
| list0_cons (a) of (a, list0 a)

#define :: list0_cons
#define nil list0_nil

(* ************* ************** *)

(*
* Always bound a pid to a name.
* The return value indicated whether the name has been used.
*)
// make sure this won't fail by reasoning out of the program
extern fun register_pid (name: string, pid_t): bool

extern fun get_pid (name: string): pid_t  // blocked if name hasn't been registered

extern fun get_self_pid (): pid_t  // this won't fail

extern fun spawn (proc: () -> ()): pid_t  // make sure this won't fail by reasoning out of the program

abstype msg_t

extern fun select (mask: list0 int): msg_t


(* ************* ************** *)

fun server (user_list: list0 string): void = let
  val mask = 0 :: 1 :: 2 :: nil
  val (port, msg) = select (mask)
in
  // logon
  case port of
  | _ when port = 0 => let
    castfn tologon (msg: msg_t): (pid_t, string)
    val (from, name) = tologon (msg)
    val new_user_list = server_logon (from, name, user_list)
  in
    server (new_user_list)
  end
  | _ when port = 1 => let
    castfn tologoff (msg: msg_t): pid_t
  



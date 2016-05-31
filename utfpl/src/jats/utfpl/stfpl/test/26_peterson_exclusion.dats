
#define CONATSCONTRIB
"https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
staload "{$CONATSCONTRIB}/conats.sats"

fun iand (x: bool, y: bool): bool = if x then y else false

typedef worker_id = [x:int | x == 0 || x == 1] int x

#define true 1
#define false 0

val flags = conats_atomarrayref_create(2, false)
val turn = conats_atomref_create {worker_id} (0)
val g = conats_atomref_create {worker_id} (0)

fun worker (id: worker_id): void = let
  val opponent = 1 - id
  val () = conats_atomarrayref_update(flags, id, true)
  val () = conats_atomref_update(turn, opponent)
  
  fun wait_loop ():<cloref1> void = let
    
    val lflag = conats_atomarrayref_get(flags, opponent)
    val lturn: worker_id = conats_atomref_get{worker_id}(turn)
  in
    if (iand(lflag = true, (lturn = opponent))) then wait_loop ()
    else ()
  end
  
  val () = wait_loop ()
  
  prval v = mc_vlock_get (0, 0, 1, 1)
  
  // do things exclusively
  val () = conats_atomref_update(g, 1)

  prval () = mc_vlock_put (v)
  
  val () = conats_atomarrayref_update(flags, id, false)
in
end


val tid1 = conats_tid_allocate ()
val tid2 = conats_tid_allocate ()
val () = conats_thread_create(worker, 0, tid1)
val () = conats_thread_create(worker, 1, tid2)


%{$
#assert main deadlockfree;

#assert main |= G sys_assertion;

%}

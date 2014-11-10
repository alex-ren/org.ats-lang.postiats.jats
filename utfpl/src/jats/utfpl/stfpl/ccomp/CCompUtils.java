package jats.utfpl.stfpl.ccomp;

/*
 * This file should be compatible with file ats-conats/conats.sats
 */


public class CCompUtils {

    /* ************* *************** */
    
    public static final String cConATSSharedCreate   = "conats_shared_create";
//    public static final String cConATSSharedFinalize = "conats_shared_finalize";
    public static final String cConATSSharedAcquire  = "conats_shared_acquire";
    public static final String cConATSSharedRelease  = "conats_shared_release";
    public static final String cConATSSharedSignal   = "conats_shared_signal";
    public static final String cConATSSharedCondwait = "conats_shared_condwait";
    
    public static final String cConATSSharedCreateCond = "conats_shared_create_cond";
    
    /* ************* *************** */

    public static final String cConATSMutexCreate   = "conats_mutex_create";
//    public static final String cConATSMutexFinalize = "conats_mutex_finalize";
    public static final String cConATSMutexAcquire  = "conats_mutex_acquire";
    public static final String cConATSMutexRelease  = "conats_mutex_release";

    /* ************* *************** */
    
    public static final String cConATSAtomRefCreate =   "conats_atomref_create";
//    public static final String cConATSAtomRefFinalize = "conats_atomref_finalize";
    public static final String cConATSAtomRefUpdate =   "conats_atomref_update";
    public static final String cConATSAtomRefGet =      "conats_atomref_get";

    /* ************* *************** */
    
    public static final String cConATSAtomArrayRefCreate   = "conats_atomarrayref_create";
    public static final String cConATSAtomArrayRefFinalize = "conats_atomarrayref_finalize";
    public static final String cConATSAtomArrayRefUpdate   = "conats_atomarrayref_update";
    public static final String cConATSAtomArrayRefGet      = "conats_atomarrayref_get";
            
    /* ************* *************** */
    
//    public static final String cConATSTidAllocate = "conats_tid_allocate";
    public static final String cConATSThreadCreate = "conats_thread_create";
    
    /* ************* *************** */
    
    public static final String cMCSetInt = "mc_set_int";
    public static final String cMCGetInt = "mc_get_int";
    
    public static final String cMCAtomicStart = "mc_atomic_start";
    public static final String cMCAtomicEnd = "mc_atomic_end";
    
    public static final String cMCAssert = "mc_assert";

    /* ************* *************** */
    
    public static final String cMCVLockViewGet = "mc_vlockview_get";
    public static final String cMCVLockViewPut = "mc_vlockview_put";
    
    /* ************* *************** */
    
	public static String sym2name(String sym) {
		if (sym.equals("+")) {
			return "add";
		} else if (sym.equals("-")) {
			return "sub";
		} else if (sym.equals("*")) {
			return "mul";
		} else if (sym.equals("/")) {
			return "div";
		} else if (sym.equals(">")) {
			return "gt";
		} else if (sym.equals(">=")) {
			return "gte";
		} else if (sym.equals("<")) {
			return "lt";
		} else if (sym.equals("<=")) {
			return "lte";
		} else if (sym.equals("=")) {
			return "eq";
        } else if (sym.equals("~")) {
            return "neg";
        } else {
			throw new Error("sym " + sym + " is not supported.");
		}
	}
	
}









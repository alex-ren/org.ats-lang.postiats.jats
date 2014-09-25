package jats.utfpl.stfpl.ccomp;


public class CCompUtils {
	// ATS layer
//	public static final String cSysGvalCreate = "sys_gval_create";
	public static final String cSysGvarCreate = "sys_gvar_create";
	public static final String cSysGvarUpdate = "sys_gvar_update";
	public static final String cSysGvarGet = "sys_gvar_get";
	
    public static final String cSysGarrCreate = "sys_garr_create";
    public static final String cSysGarrUpdate = "sys_garr_update";
    public static final String cSysGarrGet = "sys_garr_get";
    
    public static final String cSysMCSetInt = "mc_set_int";
    public static final String cSysMCGetInt = "mc_get_int";
    public static final String cSysMCAssert = "mc_assert";
	
	// MUTFPL layer
	public static final String cSysListNil = "sys_list_nil";
	public static final String cSysListCons = "sys_list_cons";
	public static final String cSysListHead = "sys_list_get_header";
	public static final String cSysListTail = "sys_list_get_tail";
	public static final String cSysListIsNil= "sys_list_is_nil";
	
	public static final String cSysTidAllocate = "sys_tid_allocate";
	// public static final String cSysTidRelease = "sys_tid_release";
	
    public static final String cSysThreadCreate = "sys_thread_create";
    
    public static final String cSysAddrSet = "sys_addr_set";
    
    public static final String cSysMutexAlloc = "sys_mutex_allocate";
    public static final String cSysMutexRelease = "sys_mutex_release";
    public static final String cSysMutexLock = "sys_mutex_lock";
    public static final String cSysMutexUnlock = "sys_mutex_unlock";
    
    public static final String cSysCondAlloc = "sys_cond_allocate";
    public static final String cSysCondRelease = "sys_cond_release";
    public static final String cSysCondWait = "sys_cond_wait";
    public static final String cSysCondSignal = "sys_cond_signal";
    public static final String cSysCondBroadcast = "sys_cond_broadcast";

	
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
        } else if (sym.equals(CCompUtils.cSysMCGetInt)) {
            return CCompUtils.cSysMCGetInt;
        } else if (sym.equals(CCompUtils.cSysMCSetInt)) {
            return CCompUtils.cSysMCSetInt;
		} else {
			throw new Error("sym " + sym + " is not supported.");
		}
	}

}









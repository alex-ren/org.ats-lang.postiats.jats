package jats.utfpl.ccomp;

import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeBool;
import jats.utfpl.patcsps.type.PATTypeFunc;
import jats.utfpl.patcsps.type.PATTypeInt;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.utils.MapScope;

public class CCompUtils {
	// ATS layer
//	public static final String cSysGvalCreate = "sys_gval_create";
	public static final String cSysGvarCreate = "sys_gvar_create";
	public static final String cSysGvarUpdate = "sys_gvar_update";
	public static final String cSysGvarGet = "sys_gvar_get";
	
    public static final String cSysGarrCreate = "sys_garr_create";
    public static final String cSysGarrUpdate = "sys_garr_update";
    public static final String cSysGarrGet = "sys_garr_get";
	
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
		} else {
			throw new Error("sym " + sym + " is not supported.");
		}
	}

    public static void populateAllFunctions(MapScope<TID> scope) {
        String func = null;
        
        /* ********* *********** ************* ************** */
        
        func = "add";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, false)));
        
        func = "sub";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, false)));
        
        func = "mul";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, false)));
        
        func = "div";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, false)));
        
        func = "lt";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeBool.cType, false)));
        
        func = "lte";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeBool.cType, false)));
        
        func = "gt";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeBool.cType, false)));
        
        func = "gte";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeBool.cType, false)));
        
        func = "eq";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeBool.cType, false)));
        
        func = "neg";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeBool.cType, false)));
        
        func = "hello";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = "mojo";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = "printx";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        /* ********* *********** ************* ************** */

//        func = cSysGvalCreate;
//        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = cSysGvarCreate;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = cSysGvarUpdate;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = cSysGvarGet;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        /* ********* *********** ************* ************** */
        
        func = cSysGarrCreate;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = cSysGarrUpdate;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = cSysGarrGet;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        /* ********* *********** ************* ************** */
        
        func = cSysAddrSet;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        /* ********* *********** ************* ************** */
        
        func = "createEvt";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));

        /* ********* *********** ************* ************** */
        
        func = cSysMutexAlloc;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = cSysMutexRelease;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = cSysMutexLock;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = cSysMutexUnlock;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        /* ********* *********** ************* ************** */
        
        func = cSysCondAlloc;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = cSysCondRelease;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = cSysCondWait;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = cSysCondSignal;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = cSysCondBroadcast;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        /* ********* *********** ************* ************** */
        
        func = "ref_allocate";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "ref_release";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "ref_get";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "ref_set";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        /* ********* *********** ************* ************** */
        
        func = cSysListNil;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = cSysListCons;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = cSysListHead;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = cSysListTail;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = cSysListIsNil;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        /* ********* *********** ************* ************** */
        
        func = cSysThreadCreate;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));

        func = "thread_join";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        /* ********* *********** ************* ************** */
        
        /* ********* *********** ************* ************** */
        
        return;
    }
}









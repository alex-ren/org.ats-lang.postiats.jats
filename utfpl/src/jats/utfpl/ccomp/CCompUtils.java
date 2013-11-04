package jats.utfpl.ccomp;

import jats.utfpl.instruction.InstructionTransformer;
import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeBool;
import jats.utfpl.patcsps.type.PATTypeFunc;
import jats.utfpl.patcsps.type.PATTypeInt;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.utils.MapScope;

public class CCompUtils {
//    public static final String createThread = "createThread";
//    public static final String allocateRef = "allocateRef";
//    public static final String releaseRef = "releaseRef";
//    private static String [] m_funcs = new String[] {
//        "add", "sub", "mul", "div"
//        , "lt", "lte", "gt", "gte", "eq"
//        , "hello", "mojo", "printx"
//        , "createEvt"
//        , createThread
//        };

    public static void populateAllFunctions(MapScope<TID> scope) {
        String func = null;
        
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
        
        func = "hello";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = "mojo";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = "printx";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(false)));
        
        func = InstructionTransformer.cSetAddr;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = InstructionTransformer.cSetArray;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = InstructionTransformer.cGetArray;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "createEvt";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = "thread_create";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "thread_join";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = InstructionTransformer.cAllocMutex;
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "mutex_release";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "mutex_lock";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "mutex_unlock";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "cond_allocate";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "cond_release";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "cond_wait";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "cond_signal";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "ref_allocate";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "ref_release";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "ref_get";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "ref_set";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        return;
    }
}









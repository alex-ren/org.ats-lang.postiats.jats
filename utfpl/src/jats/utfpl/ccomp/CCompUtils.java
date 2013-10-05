package jats.utfpl.ccomp;

import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeBool;
import jats.utfpl.patcsps.type.PATTypeFunc;
import jats.utfpl.patcsps.type.PATTypeInt;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.utils.MapScope;

public class CCompUtils {
    public static final String createThread = "createThread";
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
        
        func = "createEvt";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(true)));
        
        func = "createThread";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "mutex_allocate";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "mutex_lock";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "mutex_unlock";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "cond_allocate";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeInt.cType, true)));
        
        func = "cond_wait";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        func = "cond_signal";
        scope.addValue(func, TID.createLibFun(func, new PATTypeFunc(PATTypeSingleton.cVoidType, true)));
        
        return;
    }
}









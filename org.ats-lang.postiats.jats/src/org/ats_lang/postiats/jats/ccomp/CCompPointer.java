package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

public class CCompPointer {
    
    public static Integer atspre_sub_ptr_ptr(Ptrk p1, Ptrk p2) {
        return p1.subIndex(p2);
    }
    
    public static Integer atspre_sub_ptr0_ptr0(Ptrk p1, Ptrk p2) {
        return atspre_sub_ptr_ptr(p1, p2);
    }
    
    public static Integer atspre_sub_ptr1_ptr1(Ptrk p1, Ptrk p2) {
        return atspre_sub_ptr_ptr(p1, p2);
    }
    
    static public void populateFuncType(ATSScope<Object> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType strFunc = new FuncType(StringType.cType, null);
        FuncType sarrptrFunc = new FuncType(StringType.cType, null);

        typscope.addValue("atspre_sub_ptr_ptr", intFunc);
        typscope.addValue("atspre_sub_ptr0_ptr0", intFunc);
        typscope.addValue("atspre_sub_ptr1_ptr1", intFunc);
    }
    
    
    
}

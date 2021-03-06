package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

public class CCompPointer {
    
    public static Ptrk atspre_add_ptr_bsz(Ptrk p, Integer ofs) {
        return Ptrk.createPtrkOffset(p, ofs);
    }
    
    public static Ptrk atspre_add_ptr0_bsz(Ptrk p, Integer ofs) {
        return atspre_add_ptr_bsz(p, ofs);
    }
    
    public static Ptrk atspre_add_ptr1_bsz(Ptrk p, Integer ofs) {
        return atspre_add_ptr_bsz(p, ofs);
    }
    
    
    public static Integer atspre_sub_ptr_ptr(Ptrk p1, Ptrk p2) {
        return p1.subIndex(p2);
    }
    
    public static Integer atspre_sub_ptr0_ptr0(Ptrk p1, Ptrk p2) {
        return atspre_sub_ptr_ptr(p1, p2);
    }
    
    public static Integer atspre_sub_ptr1_ptr1(Ptrk p1, Ptrk p2) {
        return atspre_sub_ptr_ptr(p1, p2);
    }
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType, null);
        FuncType sizeFunc = new FuncType(SizeType.cType, null);
        FuncType boolFunc = new FuncType(BoolType.cType, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType ptrkFunc = new FuncType(PtrkType.cType, null);

        typscope.addValue("atspre_add_ptr_bsz", ptrkFunc);
        typscope.addValue("atspre_add_ptr0_bsz", ptrkFunc);
        typscope.addValue("atspre_add_ptr1_bsz", ptrkFunc);
        
        typscope.addValue("atspre_sub_ptr_ptr", intFunc);
        typscope.addValue("atspre_sub_ptr0_ptr0", intFunc);
        typscope.addValue("atspre_sub_ptr1_ptr1", intFunc);
    }
    
    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }
    
    
}

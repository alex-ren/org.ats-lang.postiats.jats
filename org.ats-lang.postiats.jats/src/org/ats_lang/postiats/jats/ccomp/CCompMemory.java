package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

/*
 * This file is the counter-part of file memory.cats
 */

public class CCompMemory {

    public static Ptrk atspre_malloc_gc(Integer x) {
        ArrayType arrTy = new ArrayType(DoubleType.cType0, x / 4);
        Object [] arr = arrTy.createNormalDefault();
        Ptrk ptr = Ptrk.createPtrk(arrTy, arr);
//        throw new Error("not supported");
        System.out.println("atspre_malloc_gc is not fully supported");
        return ptr;
    }

    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType ptrkFunc = new FuncType(PtrkType.cType, null);

        typscope.addValue("atspre_malloc_gc", ptrkFunc);
    }

    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }
    
}


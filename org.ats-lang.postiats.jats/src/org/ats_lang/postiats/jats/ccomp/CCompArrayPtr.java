package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ArrPsz;
import org.ats_lang.postiats.jats.value.ArrPtr;
import org.ats_lang.postiats.jats.value.Ptrk;


/*
 * This class corresponds to the file arrayptr.cats.
 */
public class CCompArrayPtr {
    
//    atstype_arrptr
//    atspre_arrpsz_get_ptrsize
//    (
//      atstype_arrpsz psz, atstype_ref asz
//    ) {
//      *(size_t*)asz = psz.size ; return (psz.ptr) ;
//    } // en of [atspre_arrpsz_get_ptrsize]
    // Version 1: for interpreter
    public static ArrPtr atspre_arrpsz_get_ptrsize(ArrPsz arrpsz, Ptrk asz) {
        int size = arrpsz.getAsz();
        asz.update(size);
        return new ArrPtr(arrpsz);
    }
    
//    // Version 2: for translator
//    public static CPtrValue atspre_arrpsz_get_ptrsize(CCompTypedefs.atstype_arrpsz psz, CPtrValue asz) {
//        ((SizeValue) asz.deRef()).copyfrom(psz.size);
//        return psz.ptr;  // omit one deepcopy
//    }
    
    // Version 1: for interpreter
//    public static ArrPtr atspre_ptr0_add_bytesize(ArrPtr parr, Integer sz) {
//        parr.addByteSize(sz);
//        return parr;    // omit one deepcopy
//    }
    
//    // Version 2: for translator
//    public static CPtrValue atspre_ptr0_add_bytesize(CPtrValue parr, SizeValue sz) {
//        parr.addByteSize(sz.getContent());
//        return parr;    // omit one deepcopy
//    }
    
    static public void populateFuncType(ATSScope<Object> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType strFunc = new FuncType(StringType.cType, null);
        FuncType sarrptrFunc = new FuncType(StringType.cType, null);

        typscope.addValue("atspre_arrpsz_get_ptrsize", sarrptrFunc);
    }

}



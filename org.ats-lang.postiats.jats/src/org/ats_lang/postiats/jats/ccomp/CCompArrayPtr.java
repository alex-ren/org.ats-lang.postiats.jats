package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ArrPsz;
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
    public static Ptrk atspre_arrpsz_get_ptrsize(ArrPsz arrpsz, Ptrk asz) {
        int size = arrpsz.getAsz();
        asz.update(size, IntType.cType0);
        return arrpsz.getPtr();
    }
    
//    ATSinline()
//    atstype_arrptr
//    atspre_arrayptr_make_arrpsz
//      (atstype_arrpsz psz) { return (psz).ptr ; }
//    // end of [atspre_arrayptr_make_arrpsz]
    public static Ptrk atspre_arrayptr_make_arrpsz(ArrPsz arrpsz) {
        return arrpsz.getPtr();
    }
    
//    ATSinline()
//    atsvoid_t0ype
//    atspre_arrayptr_free
//      (atstype_arrptr ptr) { ATS_MFREE (ptr) ; return ; }
//    // end of [atspre_arrayptr_free]
    public static void atspre_arrayptr_free(Ptrk ptr) {
        ptr.release();
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
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType ptrkFunc = new FuncType(PtrkType.cType, null);

        typscope.addValue("atspre_arrpsz_get_ptrsize", ptrkFunc);
        typscope.addValue("atspre_arrayptr_make_arrpsz", ptrkFunc);
        typscope.addValue("atspre_arrayptr_free", voidFunc);
    }

}



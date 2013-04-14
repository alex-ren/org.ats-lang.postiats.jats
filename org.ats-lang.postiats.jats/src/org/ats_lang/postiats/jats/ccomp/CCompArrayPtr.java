package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.ccomp.CCompTypedefs.CPtrValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SizeValue;
import org.ats_lang.postiats.jats.value.StructValue;

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
    public static PtrValue atspre_arrpsz_get_ptrsize(StructValue psz, PtrValue asz) {
        
        asz.deRef(psz.getItem("size").getType()).copyfrom(psz.getItem("size"));
        return (PtrValue)psz.getItem("ptr");  // omit one deepcopy
    }
    
    // Version 2: for translator
    public static CPtrValue atspre_arrpsz_get_ptrsize(CCompTypedefs.atstype_arrpsz psz, CPtrValue asz) {
        ((SizeValue) asz.deRef()).copyfrom(psz.size);
        return psz.ptr;  // omit one deepcopy
    }
    
    // Version 1: for interpreter
    public static PtrValue atspre_ptr0_add_bytesize(PtrValue parr, SizeValue sz) {
        parr.addByteSize(sz.getContent());
        return parr;    // omit one deepcopy
    }
    
    // Version 2: for translator
    public static CPtrValue atspre_ptr0_add_bytesize(CPtrValue parr, SizeValue sz) {
        parr.addByteSize(sz.getContent());
        return parr;    // omit one deepcopy
    }
}



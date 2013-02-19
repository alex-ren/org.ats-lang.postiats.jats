package org.ats_lang.postiats.jats.ccomp;

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
    public static PtrValue atspre_arrpsz_get_ptrsize(StructValue psz, PtrValue asz) {
        asz.deRef().copyfrom(psz.getItem("size"));
        return (PtrValue)psz.getItem("ptr");
    }
    
    public static PtrValue atspre_ptr0_add_bytesize(PtrValue parr, SizeValue sz) {
        parr.addByteSize(sz.getContent());
        return parr;
    }
}



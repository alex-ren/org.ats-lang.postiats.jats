package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SSizeValue;

public class CCompPointer {
    
    public static SSizeValue atspre_sub_ptr_ptr(PtrValue p1, PtrValue p2) {
        return new SSizeValue(p1.subIndex(p2));
    }
    
    public static SSizeValue atspre_sub_ptr0_ptr0(PtrValue p1, PtrValue p2) {
        return atspre_sub_ptr_ptr(p1, p2);
    }
    
    public static SSizeValue atspre_sub_ptr1_ptr1(PtrValue p1, PtrValue p2) {
        return atspre_sub_ptr_ptr(p1, p2);
    }
    
    
    
}

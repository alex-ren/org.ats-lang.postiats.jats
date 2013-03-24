package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.StringValue;

public class CCompString {

    
    public static SingletonValue atspre_fprint_string(PtrValue out, StringValue x) {
        String str = x.getContent();
        if (out.isEqual(PtrValue.c_stderr)) {
            System.err.print(str);
        } else if (out.isEqual(PtrValue.c_stdout)) {
            System.out.print(str);
        } else {
            throw new Error("Unknown FILE type");
        }
        
        return SingletonValue.VOID;       
    }
    
    public static SingletonValue atspre_print_string(StringValue x) {
        return atspre_fprint_string(PtrValue.c_stdout, x);
    }
    
    public static SingletonValue atspre_prerr_string(StringValue x) {
        return atspre_fprint_string(PtrValue.c_stderr, x);
    }
    
}


package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.StringValue;

public class CCompString {
    public static SingletonValue atspre_print_string(StringValue s) {
        String str = s.getContent();
        System.out.print(str);
        
        return SingletonValue.VOID;
        
    }
}

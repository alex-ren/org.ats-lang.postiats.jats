package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.SingletonValue;

public class CCompBasics {
    public static SingletonValue atspre_print_newline() {
        System.out.println();
        
        return SingletonValue.VOID;
        
    }
}

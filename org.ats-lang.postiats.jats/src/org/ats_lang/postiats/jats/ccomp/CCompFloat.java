package org.ats_lang.postiats.jats.ccomp;


import org.ats_lang.postiats.jats.value.*;

public class CCompFloat {

    public static SingletonValue atspre_print_double(DoubleValue x) {
        Double i = x.getContent();
        System.out.print(i);
        
        return SingletonValue.VOID;
        
    }


    

    
}

package org.ats_lang.postiats.jats.type;

import java.util.Map;

public class ATSTypeUtils {
    
    public static int calcSize(Map<?, ATSType> map) {
        int ret = 0;
        for(ATSType ty: map.values()) {
            ret += ty.getSize();
        }
        
        return ret;
    }

}

package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ArrPsz;


public class ArrPszType extends ATSTempType {

    public static final ArrPszType cType = new ArrPszType();
    
    private ArrPszType() {
    }

    @Override
    public Object createNormalDefault() {
        return new ArrPsz();
    }
    
//    @Override
//    public boolean equals(ATSType ty) {
//        if (this == ty) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    

}

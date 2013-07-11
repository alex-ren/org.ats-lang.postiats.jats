package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ArrPsz;
import org.ats_lang.postiats.jats.value.Ptrk;


public class ArrPszType extends ATSTempType {

    public static final ArrPszType cType = new ArrPszType();
    
    private ArrPszType() {
    }

    @Override
    public ArrPsz createNormalDefault() {
        return new ArrPsz();
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override 
    public String toString() {
        return ArrPsz.class.getSimpleName();
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

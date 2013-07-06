package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;

public class ArrPtrType extends ATSTempType {

    public static final ArrPtrType cType = new ArrPtrType();

    private ArrPtrType() {
    }

    @Override 
    public Object createNormalDefault() {
        return null;
    }

    public String toString() {
        return Ptrk.class.getSimpleName();
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }

    // @Override
    // public boolean equals(ATSType ty) {
    // if (this == ty) {
    // return true;
    // } else {
    // return false;
    // }
    // }
}

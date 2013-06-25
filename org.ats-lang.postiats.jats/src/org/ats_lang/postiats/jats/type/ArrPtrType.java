package org.ats_lang.postiats.jats.type;

public class ArrPtrType extends ATSTempType {

    public static final ArrPtrType cType = new ArrPtrType();

    private ArrPtrType() {
    }

    @Override
    public Object createNormalDefault() {
        return null;
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


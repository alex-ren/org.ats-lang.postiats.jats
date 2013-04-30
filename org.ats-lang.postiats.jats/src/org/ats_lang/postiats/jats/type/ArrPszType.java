package org.ats_lang.postiats.jats.type;


public class ArrPszType implements ATSType {

    public static final ArrPszType cType = new ArrPszType();
    
    private ArrPszType() {
    }
    
//    public static ArrPszType createUpdatable() {
//        return new ArrPszType(null);
//    }
//    
//    public ATSType getElementType() {
//        return m_elety;
//    }
//    
//    public void update(ATSType innerType) {
//        if (m_elety instanceof VoidType) {
//            throw new Error("non-changable");
//        } else {
//            m_elety = innerType;
//        }
//    }
        
    @Override
    public int getSize() {
        throw new Error("not supported");
    }

    @Override
    public Object createNormalDefault() {
        throw new Error("not supported");
    }

    @Override
    public Object createRefDefault() {
        throw new Error("not supported");
    }

}

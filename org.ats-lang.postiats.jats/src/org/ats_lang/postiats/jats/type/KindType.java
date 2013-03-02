package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class KindType implements ATSType {
    
    private ATSType m_type;
    private ATSType.Decorator m_dec;
    
    //  #define atstkind_type(tk) tk
    //  #define atstkind_t0ype(tk) tk
    public KindType(ATSType.Decorator dec, ATSType type) {
        m_type = type;
        m_dec = dec;
    }

    @Override
    public String toString() {
        throw new Error("to be decided");
    }
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return m_type.getSize();
    }

    @Override
    public ATSValue createDefault() {
        return m_type.createDefault();
    }
}

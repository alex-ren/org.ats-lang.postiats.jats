package org.ats_lang.postiats.jats.type;

public class KindType implements ATSType {
    
    private ATSType m_type;
    private ATSType.Decorator m_dec;
    
    public KindType(ATSType.Decorator dec, ATSType type) {
        m_type = type;
        m_dec = dec;
    }

    
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 4;
    }
}

package org.ats_lang.postiats.jats.type;

public class ParaType implements ATSType {

    private ATSType m_type;
    private ATSType.ParaDecorator m_dec;
    
    public ParaType(ATSType.ParaDecorator dec, ATSType type) {
        m_type = type;
        m_dec = dec;
    }

    
    @Override
    public int getSize() {
        return m_type.getSize();
    }

}

package org.ats_lang.postiats.jats.type;

public class BoxedType implements ATSType {

    public static final int m_size = 4;
    
    private ATSType m_type;
    
    public void setInnerType(ATSType type) {
        m_type = type;
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

}

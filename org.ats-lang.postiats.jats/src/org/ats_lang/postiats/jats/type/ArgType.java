package org.ats_lang.postiats.jats.type;

public class ArgType implements ATSType {

    private ATSType m_type;
    private ATSType.ArgDecorator m_dec;
    
    public ArgType(ArgType.ArgDecorator dec, ATSType type) {
        m_type = type;
        m_dec = dec;
    }

    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

}

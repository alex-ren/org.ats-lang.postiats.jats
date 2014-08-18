package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.ISType;

public class SIdUser implements IValPrim {
    
    private SId m_sid;
    private boolean m_from_env;
    
    public SIdUser(SId sid, boolean from_env) {
        m_sid = sid;
        m_from_env = from_env;
    }
    
    boolean isFromEnv() {
        return m_from_env;
    }

    @Override
    public ISType getType() {
        return m_sid.getType();
    }
    
    public String toStringCS() {
        return m_sid.toStringCS();
    }

}
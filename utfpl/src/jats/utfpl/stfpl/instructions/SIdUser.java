package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.csharpins.ccomp.DefaultLib;
import jats.utfpl.stfpl.stype.ISType;

/*
 * SIdUser can be viewed as a right value, which is a
 * reference to a left value.
 */
public class SIdUser implements IIdPrim {
    
    private SId m_sid;
    private boolean m_from_env;
    
    public SIdUser(SId sid, boolean from_env) {
        m_sid = sid;
        m_from_env = from_env;
    }
    
    boolean isFromEnv() {
        return m_from_env;
    }
    
    public SId getSId() {
        return m_sid;
    }

    @Override
    public ISType getType() {
        return m_sid.getType();
    }
    
    public String toStringCS() {
        if (m_from_env) {
            return "env0." + m_sid.toStringCS();
        } else {
            String str = m_sid.toStringCS();
            if (DefaultLib.s_sym_table.containsKey(str)) {
                return DefaultLib.s_sym_table.get(str);
            } else {
                return str;
            }
        }
        
    }

}

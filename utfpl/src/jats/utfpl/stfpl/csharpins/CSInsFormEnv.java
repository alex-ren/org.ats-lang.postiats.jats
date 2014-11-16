package jats.utfpl.stfpl.csharpins;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSInsFormEnv implements ICSInstruction {
    public CSSId m_name;  // This is name of the env.
    public List<CSSIdUser> m_env;
    
    public CSInsFormEnv(CSSId name, List<CSSIdUser> env) {
        m_name = name;
        m_env = env;
    }

    @Override
    public ST toST(STGroup stg) {
        
        // CCSInsFormEnv_st(type, name, args) ::= <<
        ST st = stg.getInstanceOf("CSInsFormEnv_st");
        st.add("type", m_name.getType().toSt(stg, 1));
        st.add("name", m_name.toStringCS()); 
        for (CSSIdUser id: m_env) {
            st.add("args", id.toStringCS());
        }
        
        return st;
    }
}

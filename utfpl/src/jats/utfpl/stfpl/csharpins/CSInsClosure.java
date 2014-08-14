package jats.utfpl.stfpl.csharpins;


import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSInsClosure implements ICSInstruction {
    public CSSId m_name;  // This is actually the name of the function.
    public Set<CSSId> m_env;
    
    public CSInsClosure(CSSId name, Set<CSSId> env) {
        m_name = name;
        m_env = env;
    }

    @Override
    public ST toST(STGroup stg) {
        // CSInsClosure_st(name, fun_type, args) ::= <<
        ST st = stg.getInstanceOf("CSInsClosure_st");
        st.add("name", m_name.toStringCS()); 
        for (CSSId id: m_env) {
            st.add("args", id.toStringCS());
        }
        
        st.add("fun_type", m_name.getType().toSt(stg, 1));
        return st;
    }
}

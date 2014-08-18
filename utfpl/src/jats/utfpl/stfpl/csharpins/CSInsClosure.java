package jats.utfpl.stfpl.csharpins;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSInsClosure implements ICSInstruction {
    public CSSId m_name;  // This is actually the name of the function.
    public CSSIdUser m_env;
    
    public CSInsClosure(CSSId name, CSSIdUser env) {
        m_name = name;
        m_env = env;
    }

    @Override
    public ST toST(STGroup stg) {
        // CSInsClosure_st(name, fun_type, env_name) ::= <<
        ST st = stg.getInstanceOf("CSInsClosure_st");
        st.add("name", m_name.toStringCS()); 
        st.add("env_name", m_env.toStringCS());
        st.add("fun_type", m_name.getType().toSt(stg, 1));
        return st;
    }
}

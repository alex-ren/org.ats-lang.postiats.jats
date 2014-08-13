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
        ST st = stg.getInstanceOf("CSInsClosure_st");
        return st;
    }
}

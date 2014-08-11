package jats.utfpl.stfpl.csharpins;


import java.util.Set;

public class CSInsClosure implements ICSInstruction {
    public CSSId m_name;  // This is actually the name of the function.
    public Set<CSSId> m_env;
    
    public CSInsClosure(CSSId name, Set<CSSId> env) {
        m_name = name;
        m_env = env;
    }

}

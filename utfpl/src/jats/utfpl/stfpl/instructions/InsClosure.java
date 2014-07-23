package jats.utfpl.stfpl.instructions;

import java.util.Set;

public class InsClosure implements IStfplInstruction {
    public SId m_name;  // This is actually the name of the function.
    public Set<SId> m_env;
    
    public InsClosure(SId name, Set<SId> env) {
        m_name = name;
        m_env = env;
    }

}

package jats.utfpl.stfpl.instructions;

import java.util.Set;

public class InsFormEnv implements IStfplInstruction {
    public SId m_name;  // This is name of the env.
    public Set<SId> m_env;
    
    public InsFormEnv(SId name, Set<SId> env) {
        m_name = name;
        m_env = env;
    }
}

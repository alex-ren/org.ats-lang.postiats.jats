package jats.utfpl.stfpl.instructions;

import java.util.List;

public class InsFormEnv implements IStfplInstruction {
    public SId m_name;  // This is name of the env.
    public List<SIdUser> m_env;
    
    private DefFunGroup m_grp;  // group of functions
                                // used in next stage
    
    public InsFormEnv(SId name, List<SIdUser> env) {
        m_name = name;
        m_env = env;
        m_grp = null;
    }
    
    public void setFunGroup(DefFunGroup grp) {
    	m_grp = grp;
    }
    
    public DefFunGroup getFunGroup() {
    	return m_grp;
    }
}

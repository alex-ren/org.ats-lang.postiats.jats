package jats.utfpl.stfpl.instructions;

import java.util.Set;

/*
 * Form a closure.
 */
public class InsClosure implements IStfplInstruction {
    public SId m_name;  // This is actually the name of the function.
    public SId m_env;  // name of the env
    
    public InsClosure(SId name, SId env) {
        m_name = name;
        m_env = env;
    }

}

package jats.utfpl.stfpl.instructions;

/*
 * Form a closure.
 */
public class InsClosure implements IStfplInstruction {
    public SId m_name;  // This is actually the name of the function.
    public SIdUser m_env;  // name of the env
    
    public InsClosure(SId name, SIdUser env) {
        m_name = name;
        m_env = env;
    }

}

package jats.utfpl.stfpl.instructions;

import java.util.List;

public class InsCall implements IStfplInstruction {
    public SId m_holder;
    public IValPrim m_fun;
    public List<IValPrim> m_args;
    
    public InsCall(SId holder, IValPrim fun, List<IValPrim> args) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
    }
}

package jats.utfpl.stfpl.instructions;

import java.util.List;

public class InsCond implements IStfplInstruction {
    public SId m_holder;
    public IValPrim m_cond;
    public List<IStfplInstruction> m_btrue;
    public List<IStfplInstruction> m_bfalse;  // cannot be null, but can be empty list
    
    public InsCond(SId holder, IValPrim cond,
            List<IStfplInstruction> btrue, List<IStfplInstruction> bfalse) {
        m_holder = holder;
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
    }
}

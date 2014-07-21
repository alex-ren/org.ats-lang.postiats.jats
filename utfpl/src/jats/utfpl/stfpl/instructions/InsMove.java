package jats.utfpl.stfpl.instructions;

public class InsMove implements IStfplInstruction {
    public SId m_holder;
    public IValPrim m_vp;
    
    public InsMove(IValPrim vp, SId holder) {
        m_holder = holder;
        m_vp = vp;
    }
}

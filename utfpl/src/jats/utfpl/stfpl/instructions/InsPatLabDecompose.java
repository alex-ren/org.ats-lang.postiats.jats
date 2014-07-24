package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Ilabel;

public class InsPatLabDecompose implements IStfplInstruction {
    public Ilabel m_lab;
    public SId m_holder;
    public IValPrim m_vp;
    
    public InsPatLabDecompose(Ilabel lab, SId holder, IValPrim vp) {
        m_lab = lab;
        m_holder = holder;
        m_vp = vp;
    }
}

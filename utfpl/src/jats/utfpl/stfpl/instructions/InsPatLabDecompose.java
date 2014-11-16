package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Ilabel;

public class InsPatLabDecompose implements IStfplInstruction {
    public Ilabel m_lab;
    public SId m_holder;
    public IValPrim m_vp;
    public int m_index;
    
    
    public InsPatLabDecompose(Ilabel lab, int index, SId holder, IValPrim vp) {
        m_lab = lab;
        m_index = index;
        m_holder = holder;
        m_vp = vp;
    }
}

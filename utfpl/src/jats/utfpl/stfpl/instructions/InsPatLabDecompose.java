package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Ilabel;

public class InsPatLabDecompose implements IStfplInstruction {
    public Ilabel m_lab; // The label for the item.
    // The label can be integer if the record is actually a list.
    // However, at this layer, this information is not precise since proof has been erased.
    public SId m_holder;
    public IValPrim m_vp;
    public int m_index;  // The index after proof erasure.
    
    
    public InsPatLabDecompose(Ilabel lab, int index, SId holder, IValPrim vp) {
        m_lab = lab;
        m_index = index;
        m_holder = holder;
        m_vp = vp;
    }
}

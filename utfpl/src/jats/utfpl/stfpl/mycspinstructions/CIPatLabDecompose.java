package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.Ilabel;

public class CIPatLabDecompose extends MyCspInstruction {
    public Ilabel m_lab;
    public MyCspTempID m_holder;
    public IMyCspTemp m_vp;
    
    public CIPatLabDecompose(MyCspTempID holder, IMyCspTemp vp, Ilabel lab, MyCspGroup blk) {
        super(blk);
        
        m_holder = holder;
        m_vp = vp;
        m_lab = lab;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        if (m_vp instanceof MyCspTempID) {
            ((MyCspTempID)m_vp).updateForUsage();
        }
        
        return offset;
    }

}

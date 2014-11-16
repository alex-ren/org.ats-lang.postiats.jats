package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.Ilabel;

public class CIPatLabDecompose extends MyCspInstruction {
    public Ilabel m_lab;
    public int m_index;
    public MyCspTempID m_holder;
    public IMyCspTemp m_record;
    
    public CIPatLabDecompose(MyCspTempID holder, IMyCspTemp vp, Ilabel lab, int index, MyCspGroup blk) {
        super(blk);
        
        m_holder = holder;
        m_record = vp;
        m_lab = lab;
        m_index = index;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        if (m_record instanceof MyCspTempID) {
            ((MyCspTempID)m_record).updateForUsage();
        }
        
        return offset;
    }

}

package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.mcinstruction.IMCValPrim;
import jats.utfpl.stfpl.mcinstruction.MCSId;

public class CIPatLabDecompose extends MyCspInstruction {
    public Ilabel m_lab;
    public MyCspTempID m_holder;
    public IMyCspTemp m_vp;
    
    public CIPatLabDecompose(MyCspTempID holder, IMyCspTemp vp, MyCspGroup blk) {
        super(blk);
        
        m_holder = holder;
        m_vp = vp;
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

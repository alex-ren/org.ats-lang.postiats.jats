package jats.utfpl.stfpl.mycspinstructions;


import java.util.List;

public class CIFormTuple extends MyCspInstruction {
    public List<IMyCspTemp> m_eles;
    public MyCspTempID m_holder;
    
    public CIFormTuple(List<IMyCspTemp> eles, MyCspTempID holder, MyCspGroup blk) {
        super(blk);
        
        m_holder = holder;
        m_eles = eles;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);

        for (IMyCspTemp arg: m_eles) {
            if (arg instanceof MyCspTempID) {
                ((MyCspTempID)arg).updateForUsage();
            }
        }
        
        return offset;
    }

}

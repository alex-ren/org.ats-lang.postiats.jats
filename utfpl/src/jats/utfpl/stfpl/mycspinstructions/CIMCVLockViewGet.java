package jats.utfpl.stfpl.mycspinstructions;

import java.util.List;

public class CIMCVLockViewGet extends MyCspInstruction {

    public List<IMyCspTemp> m_args;
    public MyCspTempID m_holder;

    public CIMCVLockViewGet(List<IMyCspTemp> args, MyCspTempID holder, MyCspGroup blk) {
        super(blk);
        m_args = args;
        m_holder = holder;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
     
    @Override
    public int process(int offset) {
        for (IMyCspTemp arg: m_args) {
            if (arg instanceof MyCspTempID) {
                ((MyCspTempID)arg).updateForUsage();
            }
        }
        offset = m_holder.processStack(offset);
        return offset;
        
    }

}

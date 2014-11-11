package jats.utfpl.stfpl.mycspinstructions;

import java.util.List;

public class CIMCVLockViewGet extends MyCspInstruction {

    public List<IMyCspTemp> m_args;

    public CIMCVLockViewGet(List<IMyCspTemp> args, MyCspGroup blk) {
        super(blk);
        m_args = args;

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
        
        return offset;
        
    }

}

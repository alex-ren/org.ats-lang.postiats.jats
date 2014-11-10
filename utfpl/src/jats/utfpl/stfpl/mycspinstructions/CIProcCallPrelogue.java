package jats.utfpl.stfpl.mycspinstructions;

import java.util.List;

public class CIProcCallPrelogue extends MyCspInstruction {
	public List<IMyCspTemp> m_args;
	public boolean m_isTail;

    public CIProcCallPrelogue(MyCspGroup blk, List<IMyCspTemp> args, boolean isTail) {
        super(blk);
        m_args = args;
        m_isTail = isTail;
    }

	@Override
    public Object accept(IMyCspVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        
        for (IMyCspTemp arg: m_args) {
            if (arg instanceof MyCspTempID) {
                if (!((MyCspTempID)arg).isFunc()) {
                    ((MyCspTempID)arg).updateForUsage();  // create a new CTempID
                }
            } else {
            }
        }
        return offset;
    }

}

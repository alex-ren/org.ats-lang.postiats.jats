package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.instruction.TID;

public class GrpThreadCreate extends MyCspGroup {
    public IMyCspTemp m_tid;
    public TID m_funlab;
    public IMyCspTemp m_args;
    
	public GrpThreadCreate(TID funlab) {
	    m_funlab = funlab;
    }
	
	public void setContent(IMyCspTemp tid, IMyCspTemp args) {
	    m_tid = tid;
	    m_args = args;
	}

	@Override
    public Object accept(IMyCspVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        if (m_tid instanceof MyCspTempID) {
            ((MyCspTempID)m_tid).updateForUsage();
        }
        
        if (m_args instanceof MyCspTempID) {
            ((MyCspTempID)m_args).updateForUsage();
        }
        
        return offset;
    }

}

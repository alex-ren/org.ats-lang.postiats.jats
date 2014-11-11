package jats.utfpl.stfpl.mycspinstructions;

public class CIMutexRelease extends MyCspInstruction {
	public IMyCspTemp m_mutex;

	public CIMutexRelease(MyCspGroup blk, IMyCspTemp mutex) {
	    super(blk);
	    
	    m_mutex = mutex;
    }

	@Override
    public Object accept(IMyCspInsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        if (m_mutex instanceof MyCspTempID) {
            ((MyCspTempID)m_mutex).updateForUsage();
        }
        
        return offset;
    }

}

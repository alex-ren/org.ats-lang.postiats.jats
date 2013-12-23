package jats.utfpl.csps;

public class CIMutexRelease extends CInstruction {
	public CTemp m_mutex;

	public CIMutexRelease(CBlock blk, CTemp mutex) {
	    super(blk);
	    
	    m_mutex = mutex;
    }

	@Override
    public Object accept(CSPSVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        if (m_mutex instanceof CTempID) {
            ((CTempID)m_mutex).updateForUsage();
        }
        
        return offset;
    }

}

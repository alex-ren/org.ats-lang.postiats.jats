package jats.utfpl.stfpl.mycspinstructions;

public class CICondRelease extends MyCspInstruction {
	public IMyCspTemp m_cond;

	public CICondRelease(MyCspGroup blk, IMyCspTemp cond) {
	    super(blk);
	    
	    m_cond = cond;
    }

	@Override
    public Object accept(IMyCspInsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        if (m_cond instanceof MyCspTempID) {
            ((MyCspTempID)m_cond).updateForUsage();
        }
        
        return offset;
    }

}

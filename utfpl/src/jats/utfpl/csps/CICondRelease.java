package jats.utfpl.csps;

public class CICondRelease extends CInstruction {
	public CTemp m_cond;

	public CICondRelease(CBlock blk, CTemp cond) {
	    super(blk);
	    
	    m_cond = cond;
    }

	@Override
    public Object accept(CSPSVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        if (m_cond instanceof CTempID) {
            ((CTempID)m_cond).updateForUsage();
        }
        
        return offset;
    }

}

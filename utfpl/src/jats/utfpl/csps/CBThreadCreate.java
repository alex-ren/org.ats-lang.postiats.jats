package jats.utfpl.csps;

import jats.utfpl.instruction.TID;

public class CBThreadCreate extends CBlock {
    public CTemp m_tid;
    public TID m_funlab;
    public CTemp m_args;
    
	public CBThreadCreate(TID funlab) {
	    m_funlab = funlab;
    }
	
	public void setContent(CTemp tid, CTemp args) {
	    m_tid = tid;
	    m_args = args;
	}

	@Override
    public Object accept(CSPSVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        if (m_tid instanceof CTempID) {
            ((CTempID)m_tid).updateForUsage();
        }
        
        if (m_args instanceof CTempID) {
            ((CTempID)m_args).updateForUsage();
        }
        
        return offset;
    }

}

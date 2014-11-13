package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

public class PProcThreadCreate implements PProc {
	public PExp m_tid;
	public TID m_funlab;
	public PExp m_args;
	
	public PProcThreadCreate(PExp tid, TID funlab, PExp args) {
		m_tid = tid;
		m_funlab = funlab;
		m_args = args;
	}

	@Override
    public Object accept(PNodeVisitor visitor) {
	    return visitor.visit(this);
    }

}

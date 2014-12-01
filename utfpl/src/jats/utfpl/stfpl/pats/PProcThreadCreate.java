package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PProcThreadCreate implements PProcNode {
	public PExp m_tid;
	public MCSId m_funlab;
	public PExp m_args;
	
	public PProcThreadCreate(PExp tid, MCSId funlab, PExp args) {
		m_tid = tid;
		m_funlab = funlab;
		m_args = args;
	}

	@Override
    public Object accept(PNodeVisitor visitor) {
	    return visitor.visit(this);
    }

}

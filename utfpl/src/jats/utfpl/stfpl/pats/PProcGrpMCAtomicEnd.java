package jats.utfpl.stfpl.pats;

public class PProcGrpMCAtomicEnd implements PProc {
	public PProc m_proc;
	
	public PProcGrpMCAtomicEnd(PProc proc) {
		m_proc = proc;
	}
	
	
	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}

package jats.utfpl.stfpl.pats;

public class PProcGrpMCAtomicStart implements PProc {
	public PProc m_proc;
	
	public PProcGrpMCAtomicStart(PProc proc) {
		m_proc = proc;
	}
	
	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}

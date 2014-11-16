package jats.utfpl.stfpl.pats;

public class PProcGrpMCAtomicStart implements PProc {

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}

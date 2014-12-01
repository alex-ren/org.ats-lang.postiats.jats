package jats.utfpl.stfpl.pats;

public class PNodeMCAtomicEnd implements PNode {

	public PNodeMCAtomicEnd() {}
	
	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}


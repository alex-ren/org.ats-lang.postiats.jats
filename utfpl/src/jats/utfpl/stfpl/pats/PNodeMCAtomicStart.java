package jats.utfpl.stfpl.pats;

public class PNodeMCAtomicStart implements PNode {

	public PNodeMCAtomicStart() {}
	
	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}

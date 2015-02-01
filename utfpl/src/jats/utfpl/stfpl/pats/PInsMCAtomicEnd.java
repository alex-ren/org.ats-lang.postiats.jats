package jats.utfpl.stfpl.pats;

public class PInsMCAtomicEnd extends PIns {

	public PInsMCAtomicEnd(boolean effect) {
	    super(effect);
    }

	@Override
    public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
    }

}

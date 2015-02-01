package jats.utfpl.stfpl.pats;

public class PInsMCAtomicStart extends PIns {

	public PInsMCAtomicStart(boolean effect) {
	    super(effect);
    }

	@Override
    public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
    }

}

package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

public class ReturnIns implements UtfplInstruction {
	public TID m_tid;
	
	public ReturnIns(TID tid) {
		m_tid = tid;
	}

	@Override
    public Object accept(InsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public boolean hasSideEffect() {
	    return false;
    }

}

package jats.utfpl.csps;

import java.util.List;

public class CIProcCallPrelogue extends CInstruction {
	public List<CTemp> m_args;
	public boolean m_isTail;

    public CIProcCallPrelogue(CBlock blk, List<CTemp> args, boolean isTail) {
        super(blk);
        m_args = args;
        m_isTail = isTail;
    }

	@Override
    public Object accept(CSPSVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
        
        for (CTemp arg: m_args) {
            if (arg instanceof CTempID) {
                if (!((CTempID)arg).isFunc()) {
                    ((CTempID)arg).updateForUsage();  // create a new CTempID
                }
            } else {
            }
        }
        return offset;
    }

}

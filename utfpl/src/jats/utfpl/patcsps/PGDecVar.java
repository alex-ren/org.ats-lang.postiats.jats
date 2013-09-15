package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PGDecVar implements PGDec {
    public TID m_tid;
    public PExp m_exp;  // initial value
    
    private PGDecVar(TID tid, PExp exp) {
    	m_tid = tid;
        m_exp = null;
    }
    
    public static PGDecVar create(TID tid) {
    	return new PGDecVar(tid, null);
    }
    
    public static PGDecVar createInit(TID tid, PExp exp) {
    	return new PGDecVar(tid, exp);
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}

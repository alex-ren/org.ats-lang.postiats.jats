package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;


public class PGDecVar implements PGDec {
    public MCSId m_mid;
//    public PExp m_exp;  // initial value
    
    public PGDecVar(MCSId mid/*, PExp exp*/) {
    	m_mid = mid;
//        m_exp = exp;
    }
    
//    public static PGDecVar create(MCSId mid) {
//    	return new PGDecVar(mid, null);
//    }
//    
//    public static PGDecVar createInit(MCSId mid, PExp exp) {
//    	return new PGDecVar(mid, exp);
//    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}

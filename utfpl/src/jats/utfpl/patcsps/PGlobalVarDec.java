package jats.utfpl.patcsps;

import jats.utfpl.csps.CTempID;

public class PGlobalVarDec implements PGlobalDec {
    public CTempID m_id;
    public PExp m_exp;  // initial value
    
    public PGlobalVarDec(TID )

    @Override
    public Object accept(PNodeVisitor visitor) {
        visitor.visit(this);
    }

}

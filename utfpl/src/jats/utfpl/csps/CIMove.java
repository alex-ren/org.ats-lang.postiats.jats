package jats.utfpl.csps;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CIMove implements CInstruction {
    private CTempID m_holder;
    private CTemp m_vp;

    public CIMove(CTempID holder, CTemp vp) {
        m_holder = holder;
        m_vp = vp;
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
}



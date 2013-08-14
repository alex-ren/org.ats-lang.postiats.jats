package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

public class MoveIns implements UtfplInstruction{
    public TID m_holder;
    public ValPrim m_vp;
    
    public MoveIns(TID holder, ValPrim vp) {
        m_holder = holder;
        m_vp = vp;
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasSideEffect() {
        if (m_holder.isGlobal()) {
            return true;
        } else if (m_vp instanceof TID && ((TID)m_vp).isGlobal()) {
            return true;
        } else {
            return false;
        }
    }
    

}

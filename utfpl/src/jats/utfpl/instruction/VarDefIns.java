package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

public class VarDefIns implements UtfplInstruction {

    public TID m_tid;
    
    public VarDefIns(TID tid) {
        m_tid = tid;
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

}

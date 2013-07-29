package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.List;

public class CondIns implements UtfplInstruction {
    public TID m_holder;
    public ValPrim m_cond;
    public List<UtfplInstruction> m_btrue;
    public List<UtfplInstruction> m_bfalse;
    
    public CondIns(TID holder, ValPrim cond, List<UtfplInstruction> btrue, List<UtfplInstruction> bfalse) {
        m_holder = holder;
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
    }

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

}

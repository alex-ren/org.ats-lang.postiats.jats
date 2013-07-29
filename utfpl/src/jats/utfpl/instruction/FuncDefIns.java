package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.List;

public class FuncDefIns implements UtfplInstruction {
    public TID m_name;
    public List<TID> m_paralst;
    public List<UtfplInstruction> m_body;
    public TID m_ret;
    
    
    public FuncDefIns(TID name, List<TID> paralst, List<UtfplInstruction> body, TID ret) {
        m_name = name;
        m_paralst = paralst;
        m_body = body;
        m_ret = ret;
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

}

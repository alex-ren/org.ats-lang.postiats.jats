package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.List;

public class FuncDefIns implements UtfplInstruction {
    public TID m_name;
    public List<TID> m_paralst;
    public List<UtfplInstruction> m_body;
    public TID m_ret;
    private Boolean m_hasSideEffect;  // indicating whether the body of this function has side effect.
    
    
    public FuncDefIns(TID name, List<TID> paralst, List<UtfplInstruction> body, TID ret) {
        m_name = name;
        m_paralst = paralst;
        m_body = body;
        m_ret = ret;
        m_hasSideEffect = null;
    }
    
    public void flagSideEffect() {
        m_hasSideEffect = true;
    }
    
    public boolean hasSideEffect() {
        // todo
        // now all functions have side effect.
        return true;
    }
    
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

}

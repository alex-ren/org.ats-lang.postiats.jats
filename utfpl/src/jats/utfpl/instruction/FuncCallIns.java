package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.List;

public class FuncCallIns implements UtfplInstruction {
    public TID m_holder;
    public ValPrim m_funlab;
    public List<ValPrim> m_args;
    
    public FuncCallIns(TID holder, ValPrim funlab, List<ValPrim> args) {
        m_holder = holder;
        m_funlab = funlab;
        m_args = args;
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }
}

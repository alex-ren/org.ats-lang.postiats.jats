package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.List;
import java.util.Map;

public class FuncCallIns implements UtfplInstruction {
    public TID m_holder;
    public TID m_funlab;
    public List<ValPrim> m_args;
    
    public FuncCallIns(TID holder, TID funlab, List<ValPrim> args) {
        m_holder = holder;
        m_funlab = funlab;
        m_args = args;
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasSideEffect() {
        if (m_holder.isLibFun()) {
            return false;
        } else {
            return true;  // todo check the body of function
        }
    }
    
    public FuncCallIns createSubs(Map<TID, TID> subMap) {
        return new FuncCallIns(
                InstructionProcessor.subsTID(m_holder, subMap), 
                m_funlab, 
                InstructionProcessor.subsTID(m_args, subMap));
    }
}

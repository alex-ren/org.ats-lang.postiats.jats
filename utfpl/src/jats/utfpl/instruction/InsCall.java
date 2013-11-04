package jats.utfpl.instruction;


import jats.utfpl.patcsps.type.PATType;
import jats.utfpl.patcsps.type.PATTypeFunc;

import java.util.List;
import java.util.Map;

public class InsCall implements UtfplInstruction {
    public TID m_holder;
    public TID m_funlab;
    public List<ValPrim> m_args;
    public boolean m_isTailCall;
    
    public InsCall(TID holder, TID funlab, List<ValPrim> args, boolean isTailCall) {
        m_holder = holder;
        m_funlab = funlab;
        m_args = args;
        m_isTailCall = isTailCall;
    }
    
    public boolean isRet() {
        return m_holder.isRet();
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasSideEffect() {
        return m_funlab.hasEffect();
    }
    
    public InsCall createSubs(Map<TID, TID> subMap) {
        return new InsCall(
                TID.subsTID(m_holder, subMap), 
                m_funlab, 
                InstructionProgramProcessor.subsVPLst(m_args, subMap), m_isTailCall);
    }
    
    public PATType getRetType() {
        return ((PATTypeFunc)m_funlab.getType()).getRetType();
    }

}

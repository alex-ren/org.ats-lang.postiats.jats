package jats.utfpl.csps;

import jats.utfpl.instruction.TID;

import java.util.List;

public class CIFunCall extends CInstruction {
    public TID m_funlab;  // Don't support function pointer.
    public List<CTemp> m_args;
    public CTempID m_ret;
    public boolean m_isTail;  // useless currently

    
    public CIFunCall(TID funlab, List<CTemp> args, CTempID ret, boolean isTail, CBlock blk) {
        super(blk);
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        m_isTail = isTail;

    }
    
    public boolean isRet() {
        return m_ret.isRet();
    }
    
    public boolean needStack() {
        return m_ret.isEscaped();
    }
    

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_ret.processStack(offset);

        for (CTemp arg: m_args) {
            if (arg instanceof CTempID) {
                ((CTempID)arg).updateForUsage();
            }
        }
        
        return offset;
        
    }

}


package jats.utfpl.csps;

import jats.utfpl.instruction.TID;

import java.util.List;

public class CIFunCall implements CInstruction {
    public TID m_funlab;  // Don't support function pointer.
    public List<CTemp> m_args;
    public CTempID m_ret;
    
    private CBlock m_blk;
    
    public CIFunCall(TID funlab, List<CTemp> args, CTempID ret, CBlock blk) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
        m_blk = blk;
        
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this, m_blk);
    }

    @Override
    public CBlock getBlock() {
        return m_blk;
    }
}


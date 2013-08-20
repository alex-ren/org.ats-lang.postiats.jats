package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class CProcessCallBlock extends CAdvancedBlock {
    public TID m_funlab;  // Don't support function pointer.
    public List<CTemp> m_args;
    public CTempID m_ret;
    
    public CProcessCallBlock(TID funlab, List<CTemp> args, CTempID ret, int level) {
        super(level);
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
    
    public CProcessCallBlock(TID funlab, int level) {
        super(level);
        m_funlab = funlab;
        m_args = null;
        m_ret = null;
    }
    
    public void reset(List<CTemp> args, CTempID ret) {
        m_args = args;
        m_ret = ret;
        
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
}

package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CProcessCallBlock implements CGroup {
    private TID m_funlab;  // Don't support function pointer.
    private List<CTemp> m_args;
    private CTempID m_ret;
    
    public CProcessCallBlock(TID funlab, List<CTemp> args, CTempID ret) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
    
    public CProcessCallBlock(TID funlab) {
        m_funlab = funlab;
        m_args = null;
        m_ret = null;
    }
    
    public void reset(List<CTemp> args, CTempID ret) {
        m_args = args;
        m_ret = ret;
        
    }
}

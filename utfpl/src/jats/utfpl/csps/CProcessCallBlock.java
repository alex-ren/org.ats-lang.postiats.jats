package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CProcessCallBlock implements CGroup {
    private TID m_funlab;  // Don't support function pointer.
    private List<ValPrim> m_args;
    private TID m_ret;
    
    public CProcessCallBlock(TID funlab, List<ValPrim> args, TID ret) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
}

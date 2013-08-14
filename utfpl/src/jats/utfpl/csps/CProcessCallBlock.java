package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CProcessCallBlock implements CGroup {
    private CTempID m_funlab;  // Don't support function pointer.
    private List<CTemp> m_args;
    private CTempID m_ret;
    
    public CProcessCallBlock(CTempID funlab, List<CTemp> args, CTempID ret) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
}

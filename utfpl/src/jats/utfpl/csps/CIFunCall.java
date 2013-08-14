package jats.utfpl.csps;

import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

import java.util.List;

public class CIFunCall implements CInstruction {
    private TID m_funlab;  // Don't support function pointer.
    private List<ValPrim> m_args;
    private TID m_ret;
    
    public CIFunCall(TID funlab, List<ValPrim> args, TID ret) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
}

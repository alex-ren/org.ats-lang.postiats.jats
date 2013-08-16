package jats.utfpl.csps;

import jats.utfpl.tree.TID;

import java.util.List;

public class CIFunCall implements CInstruction {
    private TID m_funlab;  // Don't support function pointer.
    private List<CTemp> m_args;
    private CTempID m_ret;
    
    public CIFunCall(TID funlab, List<CTemp> args, CTempID ret) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
}

package jats.utfpl.csps;

import java.util.List;

public class CIFunCall implements CInstruction {
    private CTempID m_funlab;  // Don't support function pointer.
    private List<CTemp> m_args;
    private CTempID m_ret;
    
    public CIFunCall(CTempID funlab, List<CTemp> args, CTempID ret) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
}

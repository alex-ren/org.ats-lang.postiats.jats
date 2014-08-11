package jats.utfpl.stfpl.csharpins;

import java.util.List;

public class CSInsCond implements ICSInstruction {
    public CSSId m_holder;
    public ICSValPrim m_cond;
    public List<ICSInstruction> m_btrue;
    public List<ICSInstruction> m_bfalse;  // cannot be null, but can be empty list
    
    public CSInsCond(CSSId holder, ICSValPrim cond,
            List<ICSInstruction> btrue, List<ICSInstruction> bfalse) {
        m_holder = holder;
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
    }
}

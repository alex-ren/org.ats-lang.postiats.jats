package jats.utfpl.stfpl.csharpins;

public class CSInsMove implements ICSInstruction {
    public CSSId m_holder;
    public ICSValPrim m_vp;
    
    public CSInsMove(ICSValPrim vp, CSSId holder) {
        m_holder = holder;
        m_vp = vp;
    }
}

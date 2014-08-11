package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.Ilabel;

public class CSInsPatLabDecompose implements ICSInstruction {
    public Ilabel m_lab;
    public  CSSId m_holder;
    public ICSValPrim m_vp;
    
    public  CSInsPatLabDecompose(Ilabel lab,  CSSId holder, ICSValPrim vp) {
        m_lab = lab;
        m_holder = holder;
        m_vp = vp;
    }
}

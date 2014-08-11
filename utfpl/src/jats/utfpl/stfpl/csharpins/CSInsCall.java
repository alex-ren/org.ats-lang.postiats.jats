package jats.utfpl.stfpl.csharpins;


import java.util.List;

public class CSInsCall implements ICSInstruction {
    public CSSId m_holder;
    public ICSValPrim m_fun;
    public List<ICSValPrim> m_args;
    
    public CSInsCall(CSSId holder, ICSValPrim fun, List<ICSValPrim> args) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
    }
}

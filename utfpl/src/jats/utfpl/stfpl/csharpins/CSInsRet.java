package jats.utfpl.stfpl.csharpins;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSInsRet implements ICSInstruction {
    public ICSValPrim m_vp;
    
    public CSInsRet(ICSValPrim vp) {
        m_vp = vp;
    }

    @Override
    public ST toST(STGroup stg) {
        // CSInsRet_st(v) ::= <<
        ST st = stg.getInstanceOf("CSInsRet_st");
        st.add("v", m_vp.toStringCS());
        return st;
    }

}

package jats.utfpl.stfpl.csharpins;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSInsTuple implements ICSInstruction {
    public List<ICSValPrim> m_elements;
    public CSSId m_holder;
    
    public CSInsTuple(List<ICSValPrim> elements, CSSId holder) {
        m_elements = elements;
        m_holder = holder;
    }
    
    @Override
    public ST toST(STGroup stg) {
        ST st = null;
        if (m_holder.m_sid.isRetHolder()) {
            // CSInsTuple_st(name, type, values) ::= <<
            st = stg.getInstanceOf("CSInsTuple_ret_st");
            
        } else {
            // CSInsTuple_ret_st(name, type, values) ::= <<
            st = stg.getInstanceOf("CSInsTuple_st");
            
        }

        st.add("type", m_holder.getType().toSt(stg, 1));
        st.add("name", m_holder.toStringCS());
        
        for (ICSValPrim vp: m_elements) {
            st.add("values", vp.toStringCS());
        }
        
        return st;
    }
}



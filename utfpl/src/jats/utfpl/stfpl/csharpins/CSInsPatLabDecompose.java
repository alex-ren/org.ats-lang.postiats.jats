package jats.utfpl.stfpl.csharpins;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import jats.utfpl.stfpl.Ilabel;

public class CSInsPatLabDecompose implements ICSInstruction {
    public Ilabel m_lab;
    public CSSId m_holder;
    public ICSValPrim m_vp;
    
    public  CSInsPatLabDecompose(Ilabel lab,  CSSId holder, ICSValPrim vp) {
        m_lab = lab;
        m_holder = holder;
        m_vp = vp;
    }
    
    @Override
    public ST toST(STGroup stg) {
        ST st = null;
        
        if (m_holder.m_sid.isRetHolder()) {
         // CSInsPatLabDecompose_ret_st(type, name, tuple_type, tuple_ele, member) ::= <<
            st = stg.getInstanceOf("CSInsPatLabDecompose_ret_st");
        } else {
         // CSInsPatLabDecompose_st(type, name, tuple_type, tuple_ele, member) ::= <<
            st = stg.getInstanceOf("CSInsPatLabDecompose_st");
        }
        
        st = stg.getInstanceOf("CSInsPatLabDecompose_st");
        st.add("type", m_holder.getType().toSt(stg, 1));
        st.add("name", m_holder.toStringCS());
        st.add("tuple_type", m_vp.getType().toSt(stg, 1));
        st.add("tuple_ele", m_vp.toStringCS());
        st.add("member", m_lab.toString());
        return st;
    }
}


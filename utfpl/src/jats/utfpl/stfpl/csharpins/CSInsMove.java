package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.csharptype.CSVoidType;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSInsMove implements ICSInstruction {
    public CSSId m_holder;
    public ICSValPrim m_vp;
    
    public CSInsMove(ICSValPrim vp, CSSId holder) {
        m_holder = holder;
        m_vp = vp;
    }
    
    @Override
    public ST toST(STGroup stg) {
        if (m_holder.m_sid.isRet()) {
            // ret_st(v) ::= <<
            ST st = stg.getInstanceOf("ret_st");
//            System.out.println("v is ============ " + m_vp.toStringCS() + "holder is " + m_holder.toStringCS());
            if (m_vp.getType() == CSVoidType.c_instance) {
                st.add("v", null);
            } else {
                st.add("v", m_vp.toStringCS());
            }
            
            return st;
        } else {
            // CSInsMove_st(type, name, v) ::= <<
            if (stg == null) {
                throw new Error("fffffffffffffffffff");
            }
            ST st = stg.getInstanceOf("CSInsMove_st");
            
            if (m_holder.getType() == null) {
                throw new Error("dddddddddddddddddddd");
            }
            if (m_holder.getType() != CSVoidType.c_instance) {
                if (st == null) {
                    throw new Error("eeeeeeeeeeeeeeeeee");
                }
                st.add("type", m_holder.getType().toSt(stg, 1));
            } else {
                throw new Error("check this");
            }
            
            st.add("name", m_holder.toStringCS());
            st.add("v", m_vp.toStringCS());
            return st;
        }

    }
}


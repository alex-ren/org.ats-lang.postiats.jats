package jats.utfpl.stfpl.csharpins;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

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
    
    @Override
    public ST toST(STGroup stg) {
        ST st = null;
        if (m_holder.m_sid.isRetHolder()) {
            // CSInsCond_ret_st(type, name, cond, btrue, bfalse) ::= <<
            st = stg.getInstanceOf("CSInsCond_ret_st"); 
        } else {
            // CSInsCond_st(type, name, cond, btrue, bfalse) ::= <<
            st = stg.getInstanceOf("CSInsCond_st"); 
        }

        st.add("type", m_holder.getType().toSt(stg, 1));
        st.add("name", m_holder.toStringCS());
        st.add("cond", m_cond.toStringCS());
        
        for (ICSInstruction ins: m_btrue) {
            st.add("btrue", ins.toST(stg));
        }
        
        if (null != m_bfalse) {
            for (ICSInstruction ins: m_bfalse) {
                st.add("bfalse", ins.toST(stg));
            }
        }
        
        
        return st;
    }
}


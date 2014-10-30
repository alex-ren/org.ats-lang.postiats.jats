package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

//import org.stringtemplate.v4.ST;
//import org.stringtemplate.v4.STGroup;

public class MCInsCond implements IMCInstruction {
    public MCSIdFun m_holder;
    public IMCValPrim m_cond;
    public List<IMCInstruction> m_btrue;
    public List<IMCInstruction> m_bfalse;  // can be null
    private Boolean m_has_effect;
    
    public MCInsCond(MCSIdFun holder, IMCValPrim cond,
            List<IMCInstruction> btrue, List<IMCInstruction> bfalse,
            Boolean has_effect) {
        m_holder = holder;
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
        m_has_effect = has_effect;
    }
    

    public void setEffectFlag(boolean flag) {
        m_has_effect = flag;
    }


    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return m_has_effect;
    }
    
//    @Override
//    public ST toST(STGroup stg) {
//        ST st = null;
//        if (m_holder.m_sid.isRetHolder()) {
//            // CSInsCond_ret_st(type, name, cond, btrue, bfalse) ::= <<
//            st = stg.getInstanceOf("CSInsCond_ret_st"); 
//        } else {
//            // CSInsCond_st(type, name, cond, btrue, bfalse) ::= <<
//            st = stg.getInstanceOf("CSInsCond_st"); 
//        }
//
//        st.add("type", m_holder.getType().toSt(stg, 1));
//        st.add("name", m_holder.toStringCS());
//        st.add("cond", m_cond.toStringCS());
//        
//        for (ICSInstruction ins: m_btrue) {
//            st.add("btrue", ins.toST(stg));
//        }
//        
//        if (null != m_bfalse) {
//            for (ICSInstruction ins: m_bfalse) {
//                st.add("bfalse", ins.toST(stg));
//            }
//        }
//        
//        
//        return st;
//    }
}


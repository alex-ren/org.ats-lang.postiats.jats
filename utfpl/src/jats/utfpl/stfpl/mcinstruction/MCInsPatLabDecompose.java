package jats.utfpl.stfpl.mcinstruction;


import jats.utfpl.stfpl.Ilabel;

public class MCInsPatLabDecompose implements IMCInstruction {
    public Ilabel m_lab;
    public int m_index;  // The index after proof erasure.
    public MCSId m_holder;
    public IMCValPrim m_vp;
    
    public  MCInsPatLabDecompose(Ilabel lab, int index, MCSId holder, IMCValPrim vp) {
        m_lab = lab;
        m_index = index;
        m_holder = holder;
        m_vp = vp;
    }

    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return false;
    }
    
//    @Override
//    public ST toST(STGroup stg) {
//        ST st = null;
//        
//        if (m_holder.m_sid.isRetHolder()) {
//         // CSInsPatLabDecompose_ret_st(type, name, tuple_type, tuple_ele, member) ::= <<
//            st = stg.getInstanceOf("CSInsPatLabDecompose_ret_st");
//        } else {
//         // CSInsPatLabDecompose_st(type, name, tuple_type, tuple_ele, member) ::= <<
//            st = stg.getInstanceOf("CSInsPatLabDecompose_st");
//        }
//        
//        st = stg.getInstanceOf("CSInsPatLabDecompose_st");
//        st.add("type", m_holder.getType().toSt(stg, 1));
//        st.add("name", m_holder.toStringCS());
//        st.add("tuple_type", m_vp.getType().toSt(stg, 1));
//        st.add("tuple_ele", m_vp.toStringCS());
//        st.add("member", m_lab.toString());
//        return st;
//    }
}


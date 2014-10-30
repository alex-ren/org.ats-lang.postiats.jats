package jats.utfpl.stfpl.mcinstruction;

//import jats.utfpl.stfpl.csharptype.CSFunType;
//import jats.utfpl.stfpl.csharptype.CSVoidType;
//
//import org.stringtemplate.v4.ST;
//import org.stringtemplate.v4.STGroup;

public class MCInsMove implements IMCInstruction {
    public MCSIdFun m_holder;
    public IMCValPrim m_vp;
    
    public MCInsMove(IMCValPrim vp, MCSIdFun holder) {
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
//        if (m_holder.m_sid.isRetHolder()) {
//            // ret_st(type, v) ::= <<
//            ST st = stg.getInstanceOf("ret_st");
//            
//            if (m_vp.getType() instanceof CSFunType) {
//                CSFunType fun_type = (CSFunType)m_vp.getType();
//                if (fun_type.isClosure()) { 
//                } else {
//                    st.add("type", fun_type.toSTFun(stg));
//                }
//            }
//            if (m_vp.getType() == CSVoidType.c_instance) {
//                st.add("v", null);
//            } else {
//                st.add("v", m_vp.toStringCS());
//            }
//            
//            return st;
//        } else {
//            // CSInsMove_st(type, name, v) ::= <<
//            ST st = stg.getInstanceOf("CSInsMove_st");
//            
//            if (m_holder.getType() != CSVoidType.c_instance) {
//                st.add("type", m_holder.getType().toSt(stg, 1));
//            } else {
//                throw new Error("check this");
//            }
//            
//            st.add("name", m_holder.toStringCS());
//            st.add("v", m_vp.toStringCS());
//            return st;
//        }
//
//    }
}


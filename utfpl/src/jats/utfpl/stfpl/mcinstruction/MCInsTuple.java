package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class MCInsTuple implements IMCInstruction {
    public List<IMCValPrim> m_elements;
    public MCSId m_holder;
    
    public MCInsTuple(List<IMCValPrim> elements, MCSId holder) {
        m_elements = elements;
        m_holder = holder;
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
//        if (m_holder.m_sid.isRetHolder()) {
//            // CSInsTuple_st(name, type, values) ::= <<
//            st = stg.getInstanceOf("CSInsTuple_ret_st");
//            
//        } else {
//            // CSInsTuple_ret_st(name, type, values) ::= <<
//            st = stg.getInstanceOf("CSInsTuple_st");
//            
//        }
//
//        st.add("type", m_holder.getType().toSt(stg, 1));
//        st.add("name", m_holder.toStringCS());
//        
//        for (ICSValPrim vp: m_elements) {
//            st.add("values", vp.toStringCS());
//        }
//        
//        return st;
//    }
}



package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

/*
 * Form an environment.
 */
public class MCInsFormEnv implements IMCInstruction {
    public MCSId m_holder;  // This is name of the env.
    public List<MCSId> m_env;
    
    public MCInsFormEnv(MCSId holder, List<MCSId> env) {
        m_holder = holder;
        m_env = env;
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
//        
//        // CCSInsFormEnv_st(type, name, args) ::= <<
//        ST st = stg.getInstanceOf("CSInsFormEnv_st");
//        st.add("type", m_name.getType().toSt(stg, 1));
//        st.add("name", m_name.toStringCS()); 
//        for (CSSIdUser id: m_env) {
//            st.add("args", id.toStringCS());
//        }
//        
//        return st;
//    }
}

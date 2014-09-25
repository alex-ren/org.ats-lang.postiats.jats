package jats.utfpl.stfpl.mcinstruction;

import java.util.Set;


public class MCInsFormEnv implements IMCInstruction {
    public MCSId m_name;  // This is name of the env.
    public Set<MCSIdUser> m_env;
    
    public MCInsFormEnv(MCSId name, Set<MCSIdUser> env) {
        m_name = name;
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
package jats.utfpl.stfpl.mcinstruction;

//import jats.utfpl.stfpl.csharptype.CSFunType;
//
//import org.stringtemplate.v4.ST;
//import org.stringtemplate.v4.STGroup;

/*
 * Form a closure.
 */
public class MCInsClosure implements IMCInstruction {
    public MCSId m_name;
    public MCSId m_fun;
    public MCSId m_env;
    
    public MCInsClosure(MCSId name, MCSId fun, MCSId env) {
        m_name = name;
        m_fun = fun;
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
//        // CSInsClosure_st(name, fun_type, env_name) ::= <<
//        ST st = stg.getInstanceOf("CSInsClosure_st");
//        st.add("name", m_name.toStringCS()); 
//        st.add("env_name", m_env.toStringCS());
//        st.add("fun_type", ((CSFunType)m_name.getType()).toSTFun(stg));
//        return st;
//    }
}

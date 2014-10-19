package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

//import jats.utfpl.stfpl.csharptype.CSFunType;
//import jats.utfpl.stfpl.csharptype.CSVoidType;
//
//import org.stringtemplate.v4.ST;
//import org.stringtemplate.v4.STGroup;

public class MCInsCall implements IMCInstruction {
    public MCSId m_holder;
    public MCSId m_fun;
    public List<IMCValPrim> m_args;
    
    public MCInsCall(MCSId holder, MCSId fun, List<IMCValPrim> args) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
    }

    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return m_fun.hasEffect();
    }

//    /*
//     * Classification:
//     * 1: return or not
//     * 2: function or closure
//     * 3: (function/closure)name or object
//     */
//    @Override
//    public ST toST(STGroup stg) {
//        ST st = null;
//        if (m_holder.m_sid.isRetHolder()) {
//            // CSInsCall_ret_st(name, type, call) ::= <<
//            st = stg.getInstanceOf("CSInsCall_ret_st");
//        } else {
//            // CSInsCall_st(name, type, call) ::= <<
//            st = stg.getInstanceOf("CSInsCall_st");
//        }
//
//        st.add("name", m_holder.toStringCS());
//        
//        if (m_holder.getType() != CSVoidType.c_instance) {
//            st.add("type", m_holder.getType().toSt(stg, 1));
//        }
//        
//        ST st_call = null;
//        CSFunType fun_type = (CSFunType)m_fun.getType();
//        if (fun_type.isClosure()) {  // is closure
//
//            // CSInsCall_call_clo_obj_st(fun_name, fun_type, args) ::= <<
//            st_call = stg.getInstanceOf("CSInsCall_call_clo_obj_st");
//            st_call.add("obj", m_fun.toStringCS());
//
//            st_call.add("fun_type", ((CSFunType)m_fun.getType()).toSTFun(stg));
//            
//            for (ICSValPrim vp : m_args) {
//                st_call.add("args", vp.toStringCS());
//            }
//            
//            // Add argument for env.
//            // closure_env_st(fun_name) ::= <<
//            ST st_env = stg.getInstanceOf("closure_env_st");
//            st_env.add("fun_name", m_fun.toStringCS());
//            st_call.add("args", st_env);
//
//        } else {  // is function
//            // CSInsCall_call_fun_st(fun_name, fun_type, args) ::= <<
//            st_call = stg.getInstanceOf("CSInsCall_call_fun_st");
//            st_call.add("fun_name", m_fun.toStringCS());
//            if (((CSSIdUser)m_fun).getSId().isUserFun() || ((CSSIdUser)m_fun).getSId().isConstant()) {
//            } else {  // not user function, need to do cast
//                st_call.add("fun_type", m_fun.getType().toSt(stg, 1));
//            }
//            
//            for (ICSValPrim vp: m_args) {
//                st_call.add("args", vp.toStringCS());
//            }
//        }
//
//        st.add("call", st_call);
//               
//        return st;
//    }
}

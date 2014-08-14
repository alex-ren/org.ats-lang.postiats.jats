package jats.utfpl.stfpl.csharpins;


import jats.utfpl.stfpl.csharptype.CSFunType;
import jats.utfpl.stfpl.csharptype.CSVoidType;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSInsCall implements ICSInstruction {
    public CSSId m_holder;
    public ICSValPrim m_fun;
    public List<ICSValPrim> m_args;
    
    public CSInsCall(CSSId holder, ICSValPrim fun, List<ICSValPrim> args) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
    }

    /*
     * Classification:
     * 1: return or not
     * 2: function or closure
     * 3: (function/closure)name or object
     */
    @Override
    public ST toST(STGroup stg) {
        ST st = null;
        if (m_holder.m_sid.isRet()) {
            // CSInsCall_ret_st(name, type, call) ::= <<
            st = stg.getInstanceOf("CSInsCall_ret_st");
        } else {
            // CSInsCall_st(name, type, call) ::= <<
            st = stg.getInstanceOf("CSInsCall_st");
        }

        st.add("name", m_holder.toStringCS());
        
        if (m_holder.getType() != CSVoidType.c_instance) {
            st.add("type", m_holder.getType().toSt(stg, 1));
        }
        
        ST st_call = null;
        CSFunType fun_type = (CSFunType)m_fun.getType();
        if (fun_type.isClosure()) {  // is closure

            // CSInsCall_call_clo_obj_st(fun_name, fun_type, args) ::= <<
            st_call = stg.getInstanceOf("CSInsCall_call_clo_obj_st");
            st_call.add("obj", m_fun.toStringCS());
            // todo: optimize: add check to see whether we can call the closure by its function name directly.
            
            st_call.add("fun_type", m_fun.getType().toSt(stg, 1));
            
            for (ICSValPrim vp : m_args) {
                st_call.add("args", vp.toStringCS());
            }
            
            // Add argument for env.
            // closure_env_st(fun_name) ::= <<
            ST st_env = stg.getInstanceOf("closure_env_st");
            st_env.add("fun_name", m_fun.toStringCS());
            st_call.add("args", st_env);

        } else {  // is function
            // CSInsCall_call_fun_st(fun_name, fun_type, args) ::= <<
            st_call = stg.getInstanceOf("CSInsCall_call_fun_st");
            st_call.add("fun_name", m_fun.toStringCS());
            if (((CSSId)m_fun).m_sid.isUserFun() || ((CSSId)m_fun).m_sid.isConstant()) {
            } else {  // not user function, need to do cast
                st_call.add("fun_type", m_fun.getType().toSt(stg, 1));
            }
            
            for (ICSValPrim vp: m_args) {
                st_call.add("args", vp.toStringCS());
            }
        }

        st.add("call", st_call);
               
        return st;
    }
}

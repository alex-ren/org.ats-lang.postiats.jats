package jats.utfpl.stfpl.csharptype;

import jats.utfpl.stfpl.staexp.FUNCLOclo;
import jats.utfpl.stfpl.staexp.Ifunclo;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSFunType implements ICSNamedType {
    private ICSTypeName m_name;
    private List<ICSType> m_paras;
    private ICSType m_ret;
    private Ifunclo m_clo;
    
    public CSFunType(ICSTypeName name, List<ICSType> paras, 
            ICSType ret, Ifunclo clo) {
        m_name = name;
        m_paras = paras;
        m_ret = ret;
        if (null == clo) {
            throw new Error("not allowed");
        }
        m_clo = clo;
        
        
    }
    
    public ICSType getRetType() {
        return m_ret;
    }

    @Override
    public ICSTypeName getName() {
        return m_name;
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        if (0 == level) {
            ST st = stg.getInstanceOf("CSObjectType_st");
            return st;
        } else {
            // CSFunType_s (name, args) ::= <<
            ST st = stg.getInstanceOf("CSFunType_st");
            st.add("name", m_name.toStringCS());
            
            // Add type argument
            for (ICSType type_arg: m_paras) {
                st.add("args", type_arg.toSt(stg, 1));
            }
            
            // Add type argument for env.
            if (m_clo.isClosure()) {
                st.add("args", stg.getInstanceOf("CSObjectType_st"));
            }
            
            // Add type for return value.
            st.add("args", m_ret.toSt(stg, 1));
            
            return st;
        }

    }
    
    public boolean isClosure() {
        return m_clo.isClosure();
    }

}

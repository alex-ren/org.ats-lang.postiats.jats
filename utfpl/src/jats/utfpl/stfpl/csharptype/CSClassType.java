package jats.utfpl.stfpl.csharptype;

import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import jats.utfpl.stfpl.Ilabel;

public class CSClassType implements ICSNamedType {
    private ICSTypeName m_name;
    
    private List<LabPatNorm> m_members;
    
    public static class LabPatNorm {
        private Ilabel m_lab;
        private ICSType m_type;
        
        public LabPatNorm(Ilabel lab, ICSType type) {
            m_lab = lab;
            m_type = type;
        }
        
        ICSType getType() {
            return m_type;
        }
    }

    
    public CSClassType(ICSTypeName name, List<LabPatNorm> members) {
        m_name = name;
        m_members = members;
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
            // CSClassType_st(name, type_args) ::= <<
            ST st = stg.getInstanceOf("CSClassType_st");
            st.add("name", m_name.toStringCS());
            for (LabPatNorm labpat: m_members) {
                st.add("type_args", labpat.m_type.toSt(stg, level));
            }
            return st;
        }

    }

}

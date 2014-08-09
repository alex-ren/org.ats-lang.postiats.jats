package jats.utfpl.stfpl.csharptype;

import java.util.List;

import jats.utfpl.stfpl.Ilabel;

public class CSClassType implements ICSNamedType {
    private ICSTypeName m_name;
    
    
    public static class LabPatNorm {
        private Ilabel m_lab;
        private ICSType m_type;
        
        public LabPatNorm(Ilabel lab, ICSType type) {
            m_lab = lab;
            m_type = type;
        }
    }
    
    private List<LabPatNorm> m_members;
    
    public CSClassType(ICSTypeName name, List<LabPatNorm> members) {
        m_name = name;
        m_members = members;
    }

    @Override
    public ICSTypeName getName() {
        return m_name;
    }

}

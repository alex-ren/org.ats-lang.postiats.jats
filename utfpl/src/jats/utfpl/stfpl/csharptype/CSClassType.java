package jats.utfpl.stfpl.csharptype;

import java.util.List;

import jats.utfpl.stfpl.Ilabel;

public class CSClassType implements ICSNamedType {
    
    static class LabPatNorm {
        private Ilabel m_lab;
        private ICSType m_type;
        
        public LabPatNorm(Ilabel lab, ICSType type) {
            m_lab = lab;
            m_type = type;
        }
    }
    
    private List<LabPatNorm> m_members;
    
    public CSClassType(List<LabPatNorm> members) {
        m_members = members;
    }

}

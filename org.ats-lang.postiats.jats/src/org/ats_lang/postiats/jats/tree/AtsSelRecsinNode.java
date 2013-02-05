package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsSelRecsinNode implements ATSNode {
    private String m_pmv;
    private ATSType m_type;
    private String m_lab;

    public AtsSelRecsinNode(String pmv, ATSType type, String lab) {
        m_pmv = pmv;
        m_type = type;
        m_lab = lab;
    }
    
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

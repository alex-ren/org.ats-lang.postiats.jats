package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AssignmentNode implements ATSNode {
    private String m_id;
    private ATSNode m_exp;
    
    public AssignmentNode(String id, ATSNode exp) {
        m_id = id;
        m_exp = exp;
    }

    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

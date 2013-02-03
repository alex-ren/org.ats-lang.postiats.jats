package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsMove implements ATSNode {
    private String m_id;
    private ATSNode m_e;
    
    public AtsInsMove(String id, ATSNode e) {
        m_id = id;
        m_e = e;
    }
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

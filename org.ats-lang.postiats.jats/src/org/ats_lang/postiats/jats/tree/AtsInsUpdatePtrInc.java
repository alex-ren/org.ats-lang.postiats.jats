package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsUpdatePtrInc implements ATSNode {
    private String m_id;
    private ATSType m_type;
    
    public AtsInsUpdatePtrInc(String id, ATSType type) {
        m_id = id;
        m_type = type;
    }
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

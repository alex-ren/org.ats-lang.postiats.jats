package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsPMove implements ATSNode {
    private String m_id;
    private ATSType m_type;
    private ATSNode m_e;
    
    public AtsInsPMove(String id, ATSType type, ATSNode e) {
        m_id = id;
        m_type = type;
        m_e = e;
    }
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

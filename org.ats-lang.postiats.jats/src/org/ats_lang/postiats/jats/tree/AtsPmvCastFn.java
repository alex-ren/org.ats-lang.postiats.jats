package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsPmvCastFn implements ATSNode {
    protected String m_id;
    protected ATSType m_type;
    protected ATSNode m_node;
    
    public AtsPmvCastFn(String id, ATSType type, ATSNode node) {
        m_id = id;
        m_type = type;
        m_node = node;
    }
    
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsDerefNode implements ATSNode {
    private ATSType m_type;
    private ATSNode m_node;

    public AtsDerefNode(ATSType type, ATSNode node) {
        m_type = type;
        m_node = node;
    }
    
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsSelArrIndNode implements ATSNode {
    private ATSNode m_node;
    private ATSType m_type;
    private String m_lab;

    public AtsSelArrIndNode(ATSNode node, ATSType type, String lab) {
        m_node = node;
        m_type = type;
        m_lab = lab;
    }
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

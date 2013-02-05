package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsPmvRefArg implements ATSNode {
    
    private ATSType.ParaDecorator m_dec;
    private ATSNode m_node;

    public AtsPmvRefArg(ATSType.ParaDecorator dec, ATSNode node) {
        m_dec = dec;
        m_node = node;
    }
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsPmvSizeofNode implements ATSNode {
    private ATSType m_type;

    public AtsPmvSizeofNode(ATSType type) {
        m_type = type;
    }
    
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

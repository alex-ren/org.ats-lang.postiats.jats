package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public class ReturnNode implements ATSNode {
    private ATSNode m_exp;
    
    public ReturnNode(ATSNode exp) {
        m_exp = exp;
    }
    

    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

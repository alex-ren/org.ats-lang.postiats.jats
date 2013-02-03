package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsStore implements ATSNode {
    private ATSNode m_dest;
    private ATSNode m_cont;
    
    public AtsInsStore(ATSNode dest, ATSNode cont) {
        m_dest = dest;
        m_cont = cont;
    }
    
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

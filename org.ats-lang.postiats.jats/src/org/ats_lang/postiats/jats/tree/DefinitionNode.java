package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class DefinitionNode implements ATSNode {
    private ATSType m_ty;
    private String m_id;
    
    public DefinitionNode(ATSType ty, String id) {
        m_ty = ty;
        m_id = id;
    }
    
    public ATSType getType() {
        return m_ty;
    }
    
    public String getID() {
        return m_id;
    }
    
    // todo initialization is VIP.
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

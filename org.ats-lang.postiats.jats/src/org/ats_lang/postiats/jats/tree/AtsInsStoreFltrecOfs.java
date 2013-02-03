package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsStoreFltrecOfs implements ATSNode {
    private String m_ida;
    private String m_idb;
    private ATSType m_type;
    private ATSNode m_e;
    
    public AtsInsStoreFltrecOfs(String ida, ATSType type, String idb, ATSNode e) {
        m_ida = ida;
        m_type = type;
        m_idb = idb;
        m_e = e;
    }
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

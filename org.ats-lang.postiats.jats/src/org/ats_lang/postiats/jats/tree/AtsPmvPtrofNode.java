package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsPmvPtrofNode implements ATSNode {
    private String m_id;
    
    public AtsPmvPtrofNode(String id) {
        m_id = id;
    }

    @Override
    // #define ATSPMVptrof(lval) (&(lval))
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncNode> funcs, ATSScope scope)x {
        // TODO Auto-generated method stub
        return null;
    }

}

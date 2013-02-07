package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsPMove implements ATSNode {
    private ATSNode m_tmp;
    private ATSType m_hit;
    private ATSNode m_val;
    
    public AtsInsPMove(ATSNode tmp, ATSType hit, ATSNode val) {
        m_tmp = tmp;
        m_hit = hit;
        m_val = val;
    }
    @Override
    // #define ATSINSpmove(tmp, hit, val) (*(hit*)tmp = val)
    // public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncNode> funcs, ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

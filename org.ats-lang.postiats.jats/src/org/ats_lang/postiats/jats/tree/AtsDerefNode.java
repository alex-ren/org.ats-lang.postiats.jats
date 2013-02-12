package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PtrValue;

public class AtsDerefNode implements ATSNode {
    private ATSType m_type;
    private ATSNode m_node;

    public AtsDerefNode(ATSType type, ATSNode node) {
        m_type = type;
        m_node = node;
    }
    
    @Override
    // #define ATSderef2(pmv, hit) (*(hit*)pmv)
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncNode> funcs, ATSScope scope) {
        PtrValue v = (PtrValue)m_node.evaluate(types, funcs, scope);
        return v.deRef();

    }

}

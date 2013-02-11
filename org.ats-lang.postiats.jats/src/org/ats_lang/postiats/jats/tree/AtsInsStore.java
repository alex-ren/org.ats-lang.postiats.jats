package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsStore implements ATSNode {
    private ATSNode m_pmv1;
    private ATSNode m_pmv2;
    
    public AtsInsStore(ATSNode pmv1, ATSNode pmv2) {
        m_pmv1 = pmv1;
        m_pmv2 = pmv2;
    }
    
    @Override
    // #define ATSINSstore(pmv1, pmv2) (pmv1 = pmv2)
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncNode> funcs, ATSScope scope) {
        if (m_pmv1 instanceof IdentifierNode) {
            ATSValue v = m_pmv2.evaluate(types, funcs, scope);
            scope.updateValue(((IdentifierNode)m_pmv1).getName(), v);
            return ATSValue.VOID;
        } else {
            throw new Error("ATSINSstore: only name is supported now");
        }
    }

}

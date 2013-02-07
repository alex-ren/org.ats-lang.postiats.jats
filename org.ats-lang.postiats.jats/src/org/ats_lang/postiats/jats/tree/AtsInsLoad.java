package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsLoad implements ATSNode {
    private ATSNode m_tmp;
    private ATSNode m_pmv;
    
    public AtsInsLoad(ATSNode tmp, ATSNode pmv) {
        m_tmp = tmp;
        m_pmv = pmv;
    }
    
    @Override
    // pats_ccomp_instrset
    // #define ATSINSload(tmp, pmv) (tmp = pmv)
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncNode> funcs, ATSScope scope) {
        if (m_tmp instanceof IdentifierNode) {
            ATSValue v = m_pmv.evaluate(types, funcs, scope);
            scope.updateValue(((IdentifierNode)m_tmp).getName(), v);
            return ATSValue.VOID;
        } else {
            throw new Error("AtsInsLoad: only name is supported now");
        }
    }

}

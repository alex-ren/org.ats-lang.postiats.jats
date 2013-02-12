package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsMove implements ATSNode {
    private String m_tmp;
    private ATSNode m_val;

    public AtsInsMove(String tmp, ATSNode val) {
        m_tmp = tmp;
        m_val = val;
    }

    @Override
    // #define ATSINSmove(tmp, val) (tmp = val)
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncNode> funcs, ATSScope scope) {
        ATSValue v = m_val.evaluate(types, funcs, scope);
        scope.updateValue(m_tmp, v);
        return SingletonValue.VOID;
    }
}

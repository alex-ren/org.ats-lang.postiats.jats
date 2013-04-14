package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.LValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsStore implements ATSNode {
    private ATSNode m_pmv1;
    private ATSNode m_pmv2;

    public AtsInsStore(ATSNode pmv1, ATSNode pmv2) {
        m_pmv1 = pmv1;
        m_pmv2 = pmv2;
    }

    @Override
    // #define ATSINSstore(pmv1, pmv2) (pmv1 = pmv2)
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        Object v2 = m_pmv2.evaluate(types, funcs, scope);
        Object v1 = m_pmv1.evaluate(types, funcs, scope);
        if (v1 instanceof LValue) {
            ((LValue)v1).updateValue(v2);
        } else {
            throw new Error ("AtsInsStore non holder appeared.");
        }
        
        return SingletonValue.VOID;

    }
}

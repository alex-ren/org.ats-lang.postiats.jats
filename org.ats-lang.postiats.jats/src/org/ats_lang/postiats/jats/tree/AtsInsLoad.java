package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsLoad implements ATSNode {
    private String m_tmp;
    private ATSNode m_pmv;
    
    public AtsInsLoad(String tmp, ATSNode pmv) {
        m_tmp = tmp;
        m_pmv = pmv;
    }
    
    @Override
    // pats_ccomp_instrset
    // #define ATSINSload(tmp, pmv) (tmp = pmv)
    public SingletonValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, LValueScope scope) {
            Object v = m_pmv.evaluate(types, funcs, scope);
            scope.addValue(m_tmp, v);
            return SingletonValue.VOID;
    }

}

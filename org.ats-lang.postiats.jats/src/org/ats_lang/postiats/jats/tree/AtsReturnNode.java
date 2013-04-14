package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.ReturnValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsReturnNode implements ATSNode {
    private ATSNode m_exp;

    public AtsReturnNode(ATSNode exp) {
        m_exp = exp;
    }

    // #define ATSreturn(x) return (x)
    // sample
    // ATSreturn(tmp21$1) ;
    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        if (m_exp != null) {
            ATSValue v = m_exp.evaluate(types, funcs, scope);
            return new ReturnValue(v.deepcopy());
        } else {
            return SingletonValue.VOID;
        }
    }

}

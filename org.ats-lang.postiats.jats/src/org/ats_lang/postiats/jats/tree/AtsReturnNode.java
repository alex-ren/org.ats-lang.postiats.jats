package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.ReturnValue;

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
            Map<String, FuncDef> funcs, ValueScope scope) {
        ATSValue v = m_exp.evaluate(types, funcs, scope);
        return new ReturnValue(v);
    }

}

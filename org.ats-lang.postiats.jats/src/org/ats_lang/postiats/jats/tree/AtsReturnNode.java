package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsReturnNode extends ATSTypeNode {
    private ATSNode m_exp;

    public AtsReturnNode(ATSNode exp) {
        super(exp.getType());
        
        m_exp = exp;
    }

    // #define ATSreturn(x) return (x)
    // sample
    // ATSreturn(tmp21$1) ;
    @Override
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        
        // Don't copy here. Copy is done where assignment takes place.
        Object v = m_exp.evaluate(types, funcs, scope);
        return v;
    }

}

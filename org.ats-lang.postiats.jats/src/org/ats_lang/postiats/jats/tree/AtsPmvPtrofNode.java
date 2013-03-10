package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PtrValue;

public class AtsPmvPtrofNode implements ATSNode {
    private String m_lval;
    
    public AtsPmvPtrofNode(String lval) {
        m_lval = lval;
    }

    @Override
    // #define ATSPMVptrof(lval) (&(lval))
    // example
//    ATStmpdec(tmp2, atstkind_t0ype(atstype_size))
//    ATSPMVptrof(tmp2)
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ValueScope scope) {
        ATSValue v = scope.getValue(m_lval);
        PtrValue pv = new PtrValue(v);
        return pv;
    }

}

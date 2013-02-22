package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.ATSScope;
import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsMoveVoid implements ATSNode {

    private ATSNode m_val;

    public AtsInsMoveVoid(ATSNode val) {
        m_val = val;
    }

    @Override
    // #define ATSINSmove_void(tmp, command) command
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        ATSValue v = m_val.evaluate(types, funcs, scope);
        return SingletonValue.VOID;
    }
    

}

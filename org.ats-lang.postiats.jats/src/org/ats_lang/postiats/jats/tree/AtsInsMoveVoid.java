package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsMoveVoid extends ATSTypeNode {

    private ATSNode m_val;

    public AtsInsMoveVoid(ATSNode val) {
        super(VoidType.cType);
        m_val = val;
    }

    @Override
    // #define ATSINSmove_void(tmp, command) command
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object v = m_val.evaluate(types, funcs, scope);
        return SingletonValue.VOID;
    }

}

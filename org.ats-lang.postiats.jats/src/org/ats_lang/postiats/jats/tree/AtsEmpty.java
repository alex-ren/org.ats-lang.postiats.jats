package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsEmpty implements ATSNode {

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ValueScope scope) {
        return SingletonValue.VOID;
    }

}
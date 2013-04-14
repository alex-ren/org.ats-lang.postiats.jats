package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsEmpty implements ATSNode {

    @Override
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        return SingletonValue.VOID;
    }
}

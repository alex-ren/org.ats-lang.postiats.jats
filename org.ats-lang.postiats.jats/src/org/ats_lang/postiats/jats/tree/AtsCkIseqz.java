package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.IntValue;

public class AtsCkIseqz implements ATSNode {
    private ATSNode m_exp;
    
    public AtsCkIseqz(ATSNode exp) {
        m_exp = exp;
    }
    
    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ValueScope scope) {
        ATSValue v = m_exp.evaluate(types, funcs, scope);
        if (v instanceof IntValue) {
            IntValue i = (IntValue) v;
            return new BoolValue(i.getContent() == 0);
        } else {
            throw new Error ("AtsCkIseqz not int type");
        }
    }

}

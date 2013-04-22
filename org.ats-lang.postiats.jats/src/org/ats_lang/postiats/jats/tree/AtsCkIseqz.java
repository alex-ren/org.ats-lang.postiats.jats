package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.value.Ptrk;

public class AtsCkIseqz implements ATSNode {
    private ATSNode m_exp;

    public AtsCkIseqz(ATSNode exp) {
        m_exp = exp;
    }

    @Override
    public Boolean evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        Object v = m_exp.evaluate(types, funcs, scope);

        if (v instanceof Ptrk) {
            System.out.println("lvalue in condition");
            v = ((Ptrk) v).getValue();
        }

        if (v instanceof Integer) {
            return (Integer) v == 0;
        } else {
            throw new Error("type mismatch in condition");
        }
    }

    @Override
    public BoolType getType() {
        return BoolType.cType;
    }

}

package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class IfNode implements ATSNode {
    private List<Choice> m_choices;
    private ATSNode m_else;
    
    public IfNode() {
        m_choices = new ArrayList<Choice>();
    }
    
    public void addIf(ATSNode exp, ATSNode block) {
        m_choices.add(new Choice(exp, block));
    }
    
    public void addElse(ATSNode block) {
        m_else = block;
    }

    private class Choice {

        ATSNode m_exp;
        ATSNode m_block;

        Choice(ATSNode exp, ATSNode block) {
            m_exp = exp;
            m_block = block;
        }
    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        for (Choice ch: m_choices) {
            ATSValue b = ch.m_exp.evaluate(types, funcs, scope);
            if (BoolValue.isTrue(b)) {
                ch.m_block.evaluate(types, funcs, scope);
                return SingletonValue.VOID;
            }
        }
        m_else.evaluate(types, funcs, scope);
        return SingletonValue.VOID;
    }
}





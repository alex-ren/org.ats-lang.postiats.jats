package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class IfNode extends ATSTypeNode {
    private List<Choice> m_choices;
    private ATSNode m_else;
    
    public IfNode() {
        super(VoidType.cType);
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
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        for (Choice ch: m_choices) {
            Object b = ch.m_exp.evaluate(types, funcs, scope);
            if (BoolType.isTrue(b, ch.m_exp.getType())) {
                ch.m_block.evaluate(types, funcs, scope);
                return SingletonValue.VOID;
            }
        }
        m_else.evaluate(types, funcs, scope);
        return SingletonValue.VOID;
    }
}





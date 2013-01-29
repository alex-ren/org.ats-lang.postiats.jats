package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

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

    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }
    
    private class Choice {

        ATSNode m_exp;
        ATSNode m_block;

        Choice(ATSNode exp, ATSNode block) {
            m_exp = exp;
            m_block = block;
        }
    }
}

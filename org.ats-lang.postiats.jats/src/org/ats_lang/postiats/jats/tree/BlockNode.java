package org.ats_lang.postiats.jats.tree;

import java.util.List;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public class BlockNode implements ATSNode {
    private List<ATSNode> m_statements;

    public void addStat(ATSNode stat) {
        m_statements.add(stat);
    }

    
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

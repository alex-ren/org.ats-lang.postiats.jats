package org.ats_lang.postiats.jats.tree;

import java.util.List;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public class FuncCallNode implements ATSNode {
    private String m_id;
    private List<ATSNode> m_args;
    
    public FuncCallNode(String id, List<ATSNode> args) {
        m_id = id;
        m_args = args;
    }

    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}

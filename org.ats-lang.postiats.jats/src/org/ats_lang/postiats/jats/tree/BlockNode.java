package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.ReturnValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

// The node holds the body of the function.
public class BlockNode implements ATSNode {
    private List<ATSNode> m_statements;

    public void addStat(ATSNode stat) {
        m_statements.add(stat);
    }
    
    public BlockNode() {
        m_statements = new ArrayList<ATSNode>();
    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        for (ATSNode state: m_statements) {
            ATSValue v = state.evaluate(types, funcs, scope);
            if (v instanceof ReturnValue) {
                return ((ReturnValue)v).getContent();
            }
        }
        
        return SingletonValue.VOID;
    }

}

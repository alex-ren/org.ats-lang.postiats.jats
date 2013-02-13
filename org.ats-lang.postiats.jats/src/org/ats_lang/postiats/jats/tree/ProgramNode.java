package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;


public class ProgramNode implements ATSNode {
	private List<ATSNode> m_statements;
	
	// The ProgramNode holds a scope so that we can populate library functions into the scope.
	public ProgramNode() {
		m_statements = new ArrayList<ATSNode>();
	}
	
	public void addStat(ATSNode stat) {
		m_statements.add(stat);
	}
	
    public void addProg(ATSNode stat) {
        m_statements.addAll(((ProgramNode)stat).getStat());
    }
    
    public List<ATSNode> getStat() {
        return m_statements;
    }

    @Override
    // Basically, it's just a list of variable definition.
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        for (ATSNode state: m_statements) {
            state.evaluate(types, funcs, scope);
        }
        return SingletonValue.VOID;
    }

}

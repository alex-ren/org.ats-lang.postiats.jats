package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;


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
	public ATSValue evaluate(ATSScope scope) {
		// todo
		// find the main function and evaluate it
		return null;
	}

}

package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

// The node holds the body of the function.
public class BlockNode extends ATSTypeNode {
    public List<ATSNode> m_statements;

    public void addStat(ATSNode stat) {
        m_statements.add(stat);
    }
    
    public BlockNode() {
    	super(VoidType.cType);
        m_statements = new ArrayList<ATSNode>();
    }
//
//	@Override
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        for (ATSNode state: m_statements) {
//        	Object v = state.evaluate(types, funcs, scope);
//            if (v instanceof AtsReturn.ReturnValue) {
//            	return (AtsReturn.ReturnValue) v;
//            }
//        }
//        return SingletonValue.VOID;
//    }
	

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}




	

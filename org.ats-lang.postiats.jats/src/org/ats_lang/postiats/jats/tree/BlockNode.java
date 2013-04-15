package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.ReturnValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

// The node holds the body of the function.
public class BlockNode extends ATSTypeNode {
    private List<ATSNode> m_statements;

    public void addStat(ATSNode stat) {
        m_statements.add(stat);
    }
    
    public BlockNode() {
    	super(VoidType.cType);
        m_statements = new ArrayList<ATSNode>();
    }

    @Override
    public ValueType evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        for (ATSNode state: m_statements) {
        	ValueType vt = state.evaluate(types, funcs, scope);
            if (vt.getValue() instanceof ReturnValue) {
            	ReturnValue rv = (ReturnValue)vt.getValue();
                return new ValueType(vt.getType(), rv.getContent());
            }
        }
        
        return new ValueType(VoidType.cType, SingletonValue.VOID);
    }

}

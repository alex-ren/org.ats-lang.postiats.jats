package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.utils.ATSScope.Entry;

public class IdentifierNode extends ATSTypeNode {
    public String m_id;
    public String m_moduleName;
    
    public IdentifierNode(Entry<ATSType> ent, String id) {
        super(ent.m_val);
        m_moduleName = ent.m_moduleName;
        m_id = id;
    }

    public String getName() {
        return m_id;
    }
    
    
//    @Override
//    public Object evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        return scope.getValue(m_id);
//    }


    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
    
    
}


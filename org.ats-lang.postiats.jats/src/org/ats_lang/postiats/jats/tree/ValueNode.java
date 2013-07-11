package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

// literal input
public class ValueNode extends ATSTypeNode {
    // support IntType, DoubleType, CharType, PtrkType(String actually), BoolType
    public Object m_v;
    public String m_text;
    
    public ValueNode(ATSType ty, Object v, String text) {
        super(ty);
        m_v = v;
        m_text = text;
    }

//    @Override
//    public Object evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        return m_v;
//    }
//    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
    
    
    
}



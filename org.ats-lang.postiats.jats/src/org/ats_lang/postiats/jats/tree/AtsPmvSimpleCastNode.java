package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.CharType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;


// For input of literal values. E.g. ATSPMVstring("123")
public class AtsPmvSimpleCastNode extends ATSTypeNode {

    public ATSNode m_node;
    
    public AtsPmvSimpleCastNode(ATSType type, ATSNode node) {
        super(type);
        m_node = node;
    }
//
//    @Override
//    // Currently, this is a non-op.
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        Object v = m_node.evaluate(types, funcs, scope);
//        ATSType toty = this.getType();
//        ATSType fromty = m_node.getType();
//        
//        if (toty instanceof IntType) {
//            Object temp = (Integer)v;
//        } else if (toty instanceof CharType) {
//            Object temp = (Character)v;
//        } else if (toty instanceof DoubleType){
//            Object temp = (Double)v;
//        } else if (toty instanceof PtrkType) {
//            Object temp = (Ptrk)v;
//        } else {
//            throw new Error("not supported");
//        }
//        return v;
//    }
    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}



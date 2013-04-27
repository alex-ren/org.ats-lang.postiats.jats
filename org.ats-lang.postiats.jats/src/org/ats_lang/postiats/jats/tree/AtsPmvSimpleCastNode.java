package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.CharType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsPmvSimpleCastNode extends ATSTypeNode {

    protected ATSNode m_node;
    
    public AtsPmvSimpleCastNode(ATSType type, ATSNode node) {
        super(type);
        m_node = node;
    }

    @Override
    // Currently, this is a non-op.
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object v = m_node.evaluate(types, funcs, scope);
        ATSType ty = this.getType();
        if (ty instanceof IntType) {
            Object temp = (Integer)v;
        } else if (ty instanceof CharType) {
            Object temp = (Character)v;
        } else if (ty instanceof DoubleType){
            Object temp = (Double)v;
        } else if (ty instanceof StringType) {
            Object temp = (Character[])v;
        } else {
            throw new Error("not supported");
        }
        return v;
    }

}

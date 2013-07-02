package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsPmvPtrofVoid extends ATSTypeNode {
    public String m_lval;

    public AtsPmvPtrofVoid(ATSType ty, String lval) {
        super(ty);
        m_lval = lval;
    }
//
//    @Override
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        System.out.println("Caution: AtsPmvPtrofVoid is used");
//        return null;
//    }

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);

    }

}

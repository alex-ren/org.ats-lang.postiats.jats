package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

public class AtsCkIseqz extends ATSTypeNode {
    public ATSNode m_exp;

    public AtsCkIseqz(ATSNode exp) {
        super(BoolType.cType);
        m_exp = exp;
    }
//
//    // #define ATSCKiseqz(x) ((x)==0)
//    @Override
//    public Boolean evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        Object v = m_exp.evaluate(types, funcs, scope);
////        System.out.println("ATSCKiseqz " + v);
//
//        if (v instanceof Ptrk) {
//            System.out.println("lvalue in condition");
//            v = ((Ptrk) v).getValue(BoolType.cType0);
//        }
//
//        if (v instanceof Integer) {
//            return (Integer) v == 0;
//        } else {
//            throw new Error("type mismatch in condition");
//        }
//    }

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}

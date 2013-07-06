package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class ATSdynload0 extends ATSTypeNode {
    public String m_dyn0;

    public ATSdynload0(ATSScope<ATSType> tyscope, String dyn0) {
        super(VoidType.cType);
        tyscope.addValue(dyn0, IntType.cType);
        m_dyn0 = dyn0;
    }

//    @Override
//    // #define ATSdynload0(flag) int flag = 0
//    public SingletonValue evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//
//        scope.addValue(m_dyn0, 0);
//
//        return SingletonValue.VOID;
//    }

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
}

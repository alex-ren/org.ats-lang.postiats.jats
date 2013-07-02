package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;


public class AtsPmvPtrof extends ATSTypeNode {
    public String m_lval;
    
    
    // ty = RefType
    public AtsPmvPtrof(ATSType ty, String lval) {
//        super(PtrkType.cType.createUpdatable(((RefType)ty).defType()));
        super(PtrkType.cType);
        m_lval = lval;
    }
//
//    @Override
//    // #define ATSPMVptrof(lval) (&(lval))
//    // example
////    ATStmpdec(tmp2, atstkind_t0ype(atstype_size))
////    ATSPMVptrof(tmp2)
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        Object v = scope.getValue(m_lval);
//        if (v instanceof Ptrk) {
//        	return RefType.ptrof((Ptrk) v);
//        } else {
//        	throw new Error("type mismatch");
//        }
//        
//    }
//    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}




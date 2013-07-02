package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

public class AtsSelArrPtrInd extends ATSTypeNode {
    public ATSNode m_pmv;
    public ATSReferableType m_tyelt;
    public ATSNode m_lab;

    public AtsSelArrPtrInd(ATSNode pmv, ATSType tyelt, ATSNode lab) {
        // member of array is actually a reference
        super(new RefType((ATSReferableType) tyelt));
        if (pmv.getType() instanceof PtrkType) {
        } else {
            System.out.println("pmv.getType() is " + pmv.getType());
            throw new Error("Check this.");
        }
        m_pmv = pmv;
        m_tyelt = (ATSReferableType)tyelt;
        m_lab = lab;
    }
//
//    @Override
//    // #define ATSselarrptrind(pmv, tyelt, lab) (((tyelt*)pmv)lab)
//    // example
//    // ATSINSxstore(tmp3$1,
//    // ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg1]),
//    // ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg2])
//    // ) ;
//    public Ptrk evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        Object pmv = m_pmv.evaluate(types, funcs, scope);  // array
//        Object asz = m_lab.evaluate(types, funcs, scope);  // index
//        
//        if (asz instanceof Ptrk) {
//            asz = ((Ptrk)asz).getValue(SizeType.cType0);
//        }
//        Integer sz = null;
//        if (asz instanceof Integer) {
//         sz = (Integer)asz;
//        } else {
//         throw new Error("Type error");
//        }
//        
//        Ptrk ret = ((Ptrk)pmv).SelArrInd(sz, m_tyelt);
//        return ret;
//
//    }
    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}


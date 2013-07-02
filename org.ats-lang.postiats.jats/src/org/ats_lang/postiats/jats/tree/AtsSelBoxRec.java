package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoxedType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

public class AtsSelBoxRec extends ATSTypeNode {
    public ATSNode m_pmv;
    public StructType m_tyrec;  // StructType
    public String m_lab;

    // tyrec = StructType
    // pmv := BoxedType(StructType) or RefType(BoxedType(StructType)))
    public AtsSelBoxRec(ATSNode pmv, StructType tyrec, String lab) {
        super(tyrec.getMember(lab));
        m_pmv = pmv;
        m_tyrec = tyrec;  // StructType
        m_lab = lab;
    }
//
//    @Override
//    // #define ATSselboxrec(pmv, tyrec, lab) (((tyrec*)pmv)->lab)
//    // example
//    // ATSINSmove(tmp5, foo2_1(ATSselboxrec(tmp3, postiats_tyrec_0, atslab$0))) ;
//    // ATSINSmove(tmp7, foo2_1(ATSselboxrec(tmpref4, postiats_tyrec_0, atslab$0))) ;
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
////    	System.out.println("==============AtsSelBoxRec");
//        // x := RefType (BoxedType (StructType)) => x : Ptrk
//        if (m_pmv.getType() instanceof RefType) {
////        	System.out.println("==============AtsSelBoxRec-0001");
//            Object p = m_pmv.evaluate(types, funcs, scope);
////            System.out.println("==============AtsSelBoxRec-0002");
//            Object m = RefType.getValue(p, BoxedType.cType);
////            System.out.println("==============AtsSelBoxRec-0003");
//            @SuppressWarnings("unchecked")
//            Map<String, Object> v = (Map<String, Object>) m;
////            System.out.println("==============AtsSelBoxRec-0004");
//            return v.get(m_lab);
//        } else {  // x := BoxType (StructType) => x : Map
//            @SuppressWarnings("unchecked")
//            Map<String, Object> v = (Map<String, Object>) m_pmv.evaluate(types,
//                    funcs, scope);
//            return v.get(m_lab);
//        }
//
//    }
    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }

}



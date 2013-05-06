package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

public class AtsSelBoxRec extends ATSTypeNode {
    private ATSNode m_pmv;
    private StructType m_tyrec;  // StructType
    private String m_lab;

    // tyrec = StructType
    // pmv := BoxedType(StructType) or RefType(BoxedType(StructType)))
    public AtsSelBoxRec(ATSNode pmv, StructType tyrec, String lab) {
        super(tyrec.getMember(lab));
        m_pmv = pmv;
        m_tyrec = tyrec;  // StructType
        m_lab = lab;
    }

    // #define ATSselboxrec(pmv, tyrec, lab) (((tyrec*)pmv)->lab)
    // example
    // ATSINSmove(tmp5, foo2_1(ATSselboxrec(tmp3, postiats_tyrec_0, atslab$0))) ;
    // ATSINSmove(tmp7, foo2_1(ATSselboxrec(tmpref4, postiats_tyrec_0, atslab$0))) ;
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {

        // x := RefType (BoxedType (StructType)) => x : Ptrk
        if (m_pmv.getType() instanceof RefType) {
            Object p = m_pmv.evaluate(types, funcs, scope);
            Object m = RefType.getValue(p, m_tyrec);
            @SuppressWarnings("unchecked")
            Map<String, Object> v = (Map<String, Object>) m;
            return v.get(m_lab);
        } else {  // x := BoxType (StructType) => x : Map
            @SuppressWarnings("unchecked")
            Map<String, Object> v = (Map<String, Object>) m_pmv.evaluate(types,
                    funcs, scope);
            return v.get(m_lab);
        }

    }

}

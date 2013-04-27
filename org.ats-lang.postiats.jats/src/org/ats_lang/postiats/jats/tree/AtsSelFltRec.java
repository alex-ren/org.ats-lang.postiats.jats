package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;


public class AtsSelFltRec extends ATSTypeNode {
    private ATSNode m_pmv;
    private StructType m_tyrec;  // type of structure
    private String m_lab;

    // pmv := StructType or RefType(StructType)
    // tyrec = StructType
    public AtsSelFltRec(ATSNode pmv, StructType tyrec, String lab) {
        super(tyrec.getMember(lab));
        m_pmv = pmv;
        m_tyrec = tyrec;  // StructType
        m_lab = lab;
        
        if (m_pmv.getType() instanceof RefType) {
            this.updateType(new RefType(this.getType()));
        }
    }
    
    @Override
    // #define ATSselfltrec(pmv, tyrec, lab) ((pmv).lab)
    // example
    
//    typedef
//    struct {
//    atstkind_t0ype(atstype_int) atslab$0; 
//    atstkind_t0ype(atstype_int) atslab$1; 
//    } postiats_tyrec_0 ;
//    
//    atsvoid_t0ype
//    loop_1 (atsrefarg1_type(postiats_tyrec_0) arg0, atstkind_t0ype(atstype_int) arg1)
//    
//    ATSselfltrec(ATSderef(arg0), postiats_tyrec_0, atslab$0)
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {

        // v := RefType (StructType) or StructType => v : Map
        @SuppressWarnings("unchecked")
        Map<String, Object> v = (Map<String, Object>) m_pmv.evaluate(types,
                funcs, scope);

        if (m_pmv.getType() instanceof RefType) {
            if (m_tyrec.getMember(m_lab) instanceof StructType) {
                return v.get(m_lab);  // v.get(m_lab) : Map
            } else {
                Ptrk.StructMember sm = new Ptrk.StructMember(v, m_lab);
                Ptrk refv = new Ptrk(sm);
                return refv;
            }
        } else {
            return v.get(m_lab);
        }

    }

}

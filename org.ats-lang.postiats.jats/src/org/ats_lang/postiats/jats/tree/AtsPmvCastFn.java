package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsPmvCastFn extends ATSTypeNode {
    protected String m_d2c;
    protected ATSType m_hit;
    protected ATSNode m_arg;

    // #define ATSPMVcastfn(d2c, hit, arg) ((hit)arg)
    // example
    //
    // typedef void *atstype_ptrk ;
    // ATStmpdec(tmp12$2, atstkind_type(atstype_ptrk)) ;
    // ATSPMVcastfn(cast, atstkind_type(atstype_ptrk), tmp12$2)
    // hit = not RefType
    public AtsPmvCastFn(String d2c, ATSType hit, ATSNode arg) {
        super(hit);
        m_d2c = d2c;
        m_hit = hit;
        m_arg = arg;
    }

    @Override
    // AtsPmvCastFn is a non-op.
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object v = m_arg.evaluate(types, funcs, scope);
//        System.out.println("==cast " + m_arg.getType() + " to " + m_hit);
        

        if (m_d2c.equals("cast")) {
            ATSType arg_type = m_arg.getType();

            if (arg_type == m_hit) {
//                throw new Error("check this case");
                // return v.deepcopy();
            }
            
            if (arg_type instanceof RefType) {
                throw new Error("check this case");
            }

            // ptr2string
            if (arg_type instanceof ArrPtrType) {
                throw new Error("check this case");
            }

            // encoding type conversion here
            // if (m_hit instanceof ATSPrimType && v instanceof PrimValue) {
            // return ((ATSPrimType)m_hit).castFrom((PrimValue)v);
            // } else {
            // throw new Error ("Casting unsupported: cast");
            // }
            return v;
        } else if (m_d2c.equals("string2ptr")) {
            if (!m_arg.getType().equals(PtrkType.cType)) {
                throw new Error("check this case");
            }
            if (!m_hit.equals(PtrkType.cType)) {
                throw new Error("check this case");
            }
            return v;
        } else if (m_d2c.equals("ptr1_of_ptr0")) {
            if (!m_arg.getType().equals(PtrkType.cType)) {
                throw new Error("check this case");
            }
            if (!m_hit.equals(PtrkType.cType)) {
                throw new Error("check this case");
            }
            return v;
        } else if (m_d2c.equals("string1_of_string0")) {
            if (!m_arg.getType().equals(PtrkType.cType)) {
                throw new Error("check this case");
            }
            if (!m_hit.equals(PtrkType.cType)) {
                throw new Error("check this case");
            }
            return v;
        } else {
            throw new Error("Unknown cast: " + m_d2c);
        }
    }  // arrayptr2ptr

}

package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.PrimType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.StringValue;

public class AtsPmvCastFn implements ATSNode {
    protected String m_d2c;
    protected ATSType m_hit;
    protected ATSNode m_arg;
    
    // #define ATSPMVcastfn(d2c, hit, arg) ((hit*)arg)
    // example
    // 
//    typedef void *atstype_ptrk ;
//    ATStmpdec(tmp12$2, atstkind_type(atstype_ptrk)) ;
//    ATSPMVcastfn(cast, atstkind_type(atstype_ptrk), tmp12$2)
    public AtsPmvCastFn(String d2c, ATSType hit, ATSNode arg) {
        m_d2c = d2c;
        m_hit = hit;
        m_arg = arg;
    }

    @Override
    // AtsPmvCastFn is a non-op.
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        ATSValue v = m_arg.evaluate(types, funcs, scope);
        
        if (m_d2c.equals("cast")) {
            // System.out.println("==cast " + v.getType() + " to " + m_hit);
            if (v.getType() == m_hit) {
                return v.deepcopy();
            }
            
            // ptr2string
            if (v instanceof PtrValue && m_hit == StringType.cType) {
                return ((PtrValue)v).toStringValue();
            }
            if (m_hit instanceof PrimType && v instanceof PrimValue) {
                return ((PrimType)m_hit).castFrom((PrimValue)v);
            } else {
                throw new Error ("Casting unsupported: cast");
            }
        } else if (m_d2c.equals("string2ptr")) {
            return ((StringValue) v).toPtrValue();
        } else if (m_d2c.equals("ptr1_of_ptr0")) {
            return v;  
        } else if (m_d2c.equals("string1_of_string0")) {
            return v; 
        } else {
            throw new Error ("Unknown cast: " + m_d2c);
        }
    }

}

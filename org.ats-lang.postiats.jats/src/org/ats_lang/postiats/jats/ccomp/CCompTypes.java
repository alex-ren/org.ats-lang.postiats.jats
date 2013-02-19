/*
 * The class corresponds to the file
 * Postiats/ccomp/runtime/pats_ccomp_typedefs.h
 */

package org.ats_lang.postiats.jats.ccomp;

import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.type.*;

public class CCompTypes {
    public static Map<String, ATSType> getLibTypes() {
        Map<String, ATSType> types = new HashMap<String, ATSType>();
        
        ATSType atstype_ptrk = new PtrType();
        types.put("atstype_ptrk", atstype_ptrk);
        
        ATSType atsvoid_t0ype = VoidType.cType;
        types.put("atsvoid_t0ype", atsvoid_t0ype);
        
        ATSType atstype_int = IntType.cType;
        types.put("atstype_int", atstype_int);
        
        ATSType atstype_double = DoubleType.cType;
        types.put("atstype_double", atstype_double);
        
        ATSType atstype_bool = BoolType.cType;
        types.put("atstype_bool", atstype_bool);
        
        
        return types;
    }

}

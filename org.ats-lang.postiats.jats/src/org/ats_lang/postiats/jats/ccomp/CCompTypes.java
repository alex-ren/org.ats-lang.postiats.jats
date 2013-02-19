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

        types.put("atstype_ptrk", CCompTypedefs.atstype_ptrk);
        
        types.put("atsvoid_t0ype", CCompTypedefs.atsvoid_t0ype);
        
        types.put("atstype_int", CCompTypedefs.atstype_int);
        
        types.put("atstype_double", CCompTypedefs.atstype_double);
        
        types.put("atstype_bool", CCompTypedefs.atstype_bool);
        
        types.put("atstype_arrptr", CCompTypedefs.atstype_arrptr);
        
        types.put("atstype_size", CCompTypedefs.atstype_size);
        
        types.put("atstype_arrpsz", CCompTypedefs.atstype_arrpsz);
        
        
        return types;
    }

}

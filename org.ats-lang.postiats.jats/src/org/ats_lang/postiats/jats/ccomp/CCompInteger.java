package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PrimValue;

/*
 * This file is the counter-part of file integer.cats
 */
public class CCompInteger {
    // #define atspre_g0int2int_int_int(x) ((atstype_int)(x))
    // #define atspre_g0int2int_int_lint(x) ((atstype_lint)(x))
    // #define atspre_g0int2int_int_llint(x) ((atstype_llint)(x))
    // #define atspre_g0int2int_int_ssize(x) ((atstype_ssize)(x))
    // #define atspre_g0int2int_int_intptr(x) ((atstype_intptr)(x))
    // //
    // #define atspre_g1int2int_int_int atspre_g0int2int_int_int
    // #define atspre_g1int2int_int_lint atspre_g0int2int_int_lint
    // #define atspre_g1int2int_int_llint atspre_g0int2int_int_llint
    // #define atspre_g1int2int_int_ssize atspre_g0int2int_int_ssize
    // #define atspre_g1int2int_int_intptr atspre_g0int2int_int_intptr

    public static IntValue atspre_g0int2int_int_int(ATSValue x) {
        if (x instanceof PrimValue) {
            return IntValue.castFrom((PrimValue)x);
        } else {
            throw new Error(
                    "CCompInteger::atspre_g0int2int_int_int type conversion not supported.");
        }

    }
    
    public static BoolValue atspre_g0int_gt_int(IntValue x1, IntValue x2) {
        Integer i1 = x1.getContent();
        Integer i2 = x2.getContent();
        
        return new BoolValue(i1 > i2);
        
    }
    
    

}

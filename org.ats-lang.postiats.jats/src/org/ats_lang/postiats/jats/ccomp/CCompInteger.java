package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.SizeValue;

/*
 * This file is the counter-part of file integer.cats
 */
public class CCompInteger {

    public static IntValue atspre_g0int2int_int_int(ATSValue x) {
        if (x instanceof PrimValue) {
            return IntValue.castFromV((PrimValue)x);
        } else {
            throw new Error(
                    "CCompInteger::atspre_g0int2int_int_int type conversion not supported.");
        }

    }
    
    public static SizeValue atspre_g0int2uint_int_size(ATSValue x) {
        if (x instanceof PrimValue) {
            return SizeValue.castFromV((PrimValue)x);
        } else {
            throw new Error(
                    "CCompInteger::atspre_g0int2uint_int_size type conversion not supported.");
        }

    }
    
    public static BoolValue atspre_g0int_gt_int(IntValue x1, IntValue x2) {
        Integer i1 = x1.getContent();
        Integer i2 = x2.getContent();
        
        return new BoolValue(i1 > i2);
        
    }
    
    public static IntValue atspre_g0int_add_int(IntValue x1, IntValue x2) {
        Integer i1 = x1.getContent();
        Integer i2 = x2.getContent();
        
        return new IntValue(i1 + i2);
        
    }

    public static IntValue atspre_g0int_sub_int(IntValue x1, IntValue x2) {
        Integer i1 = x1.getContent();
        Integer i2 = x2.getContent();
        
        return new IntValue(i1 - i2);
        
    }
    public static IntValue atspre_g0int_mul_int(IntValue x1, IntValue x2) {
        Integer i1 = x1.getContent();
        Integer i2 = x2.getContent();
        
        return new IntValue(i1 * i2);
        
    }
    
    public static IntValue atspre_g0int_div_int(IntValue x1, IntValue x2) {
        Integer i1 = x1.getContent();
        Integer i2 = x2.getContent();
        
        return new IntValue(i1 / i2);
        
    }
    
    public static SingletonValue atspre_print_int(IntValue x) {
        Integer i = x.getContent();
        System.out.print(i);
        
        return SingletonValue.VOID;
        
    }
    
    public static SizeValue atspre_g0uint_mul_size(SizeValue x1, SizeValue x2) {
        Integer i1 = x1.getContent();
        Integer i2 = x2.getContent();
        
        return new SizeValue(i1 * i2);
        
    }
    
    
    
    
    
}

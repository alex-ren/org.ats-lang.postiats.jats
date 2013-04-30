package org.ats_lang.postiats.jats.ccomp;


import java.util.Map;

import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;


/*
 * This file is the counter-part of file integer.cats
 */
public class CCompInteger {

    public static Integer atspre_g0int2int_int_int(Object x) {
        return IntType.castFrom(x);
    }
    
    public static Integer atspre_g1int2int_int_int(Object x) {
        return atspre_g0int2int_int_int(x);
    }
    
    public static Integer atspre_g0int2uint_int_size(Object x) {
        return SizeType.castFrom(x);
    }
    
 // ===================================================
    
    public static Boolean atspre_g0int_gt_int(Integer x1, Integer x2) {
        return x1 > x2;
    }
    
    public static Boolean atspre_g0int_gte_int(Integer x1, Integer x2) {
        return x1 >= x2;
    }
    
    public static Boolean atspre_g0int_lt_int(Integer x1, Integer x2) {
        return x1 < x2;
        
    }
    
    public static Boolean atspre_g0int_lte_int(Integer x1, Integer x2) {
        return x1 <= x2;
    }
    
 // ===================================================
    
    public static Integer atspre_g0int_add_int(Integer x1, Integer x2) {
        return x1 + x2;
        
    }

    public static Integer atspre_g0int_sub_int(Integer x1, Integer x2) {
        return x1 - x2;
    }
    public static Integer atspre_g0int_mul_int(Integer x1, Integer x2) {
        return x1 * x2;
    }
    
    public static Integer atspre_g0int_div_int(Integer x1, Integer x2) {
        return x1 / x2;
    }
    
    // ===================================================
    
    public static SingletonValue atspre_print_int(Integer x) {
        System.out.print(x);
        
        return SingletonValue.VOID;
        
    }
    
    public static Integer atspre_g0uint_mul_size(Integer x1, Integer x2) {
        return x1 * x2;
        
    }
    
    // ===================================================
    
    public static Boolean atspre_g1int_gt_int(Integer x1, Integer x2) {
        return atspre_g0int_gt_int(x1, x2);
    }
    
    public static Boolean atspre_g1int_gte_int(Integer x1, Integer x2) {
        return atspre_g0int_gte_int(x1, x2);
    }
    
    public static Boolean atspre_g1int_lt_int(Integer x1, Integer x2) {
        return atspre_g0int_lt_int(x1, x2);
    }
    
    public static Boolean atspre_g1int_lte_int(Integer x1, Integer x2) {
        return atspre_g0int_lte_int(x1, x2);
    }
    
    // ===================================================
    
    public static Integer atspre_g1int_add_int(Integer x1, Integer x2) {
        return atspre_g0int_add_int(x1, x2);
    }
    public static Integer atspre_g1int_sub_int(Integer x1, Integer x2) {
        return atspre_g0int_sub_int(x1, x2);
    }
    public static Integer atspre_g1int_mul_int(Integer x1, Integer x2) {
        return atspre_g0int_mul_int(x1, x2);
    }
    public static Integer atspre_g1int_div_int(Integer x1, Integer x2) {
        return atspre_g0int_div_int(x1, x2);
    }
    
 // ===================================================
    public static Integer atspre_g0uint2uint_size_size(Integer x) {
        return x;
    }
    
    static public void populateFuncType(ATSScope<Object> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        
        
        
        typscope.addValue("atspre_g0int2int_int_int", intFunc);
        typscope.addValue("atspre_g1int2int_int_int", intFunc);
        typscope.addValue("atspre_g0int2uint_int_size", sizeFunc);
        
        typscope.addValue("atspre_g0int_gt_int", boolFunc);
        typscope.addValue("atspre_g0int_gte_int", boolFunc);
        typscope.addValue("atspre_g0int_lt_int", boolFunc);
        typscope.addValue("atspre_g0int_lte_int", boolFunc);
        
        typscope.addValue("atspre_g0int_add_int", intFunc);
        typscope.addValue("atspre_g0int_sub_int", intFunc);
        typscope.addValue("atspre_g0int_mul_int", intFunc);
        typscope.addValue("atspre_g0int_div_int", intFunc);
        
        typscope.addValue("atspre_print_int", voidFunc);
        typscope.addValue("atspre_g0uint_mul_size", sizeFunc);
        
        typscope.addValue("atspre_g1int_gt_int", boolFunc);
        typscope.addValue("atspre_g1int_gte_int", boolFunc);
        typscope.addValue("atspre_g1int_lt_int", boolFunc);
        typscope.addValue("atspre_g1int_lte_int", boolFunc);
        
        typscope.addValue("atspre_g1int_add_int", intFunc);
        typscope.addValue("atspre_g1int_sub_int", intFunc);
        typscope.addValue("atspre_g1int_mul_int", intFunc);
        typscope.addValue("atspre_g1int_div_int", intFunc);
        
        
    }
    
}

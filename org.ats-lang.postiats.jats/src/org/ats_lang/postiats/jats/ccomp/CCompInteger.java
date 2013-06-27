package org.ats_lang.postiats.jats.ccomp;


import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;
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
    
    public static Integer atspre_g1int2uint_int_size(Object x) {
        return atspre_g0int2uint_int_size(x);
    }

    // ===================================================

    public static Boolean atspre_g0int_gt_int(Integer x1, Integer x2) {
        return x1 > x2;
    }

    public static Boolean atspre_g0int_gte_int(Integer x1, Integer x2) {
        return x1 >= x2;
    }

    public static Boolean atspre_g0int_eq_int(Integer x1, Integer x2) {
        return x1.equals(x2);
    }

    public static Boolean atspre_g0int_neq_int(Integer x1, Integer x2) {
        return !x1.equals(x2);
    }

    public static Boolean atspre_g0int_lt_int(Integer x1, Integer x2) {
        return x1 < x2;

    }

    public static Boolean atspre_g0int_lte_int(Integer x1, Integer x2) {
        return x1 <= x2;
    }

    // ===================================================

    public static Integer atspre_g0int_add_int(Integer x1, Integer x2) {
        // System.out.println("x1 is " + x1 + ", x2 is " + x2);
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
    public static SingletonValue atspre_fprint_int(Ptrk out, Integer x) {
        if (out == Ptrk.c_stderr) {
            System.err.print(x);
        } else if (out == Ptrk.c_stdout) {
            System.out.print(x);
        } else {
            throw new Error("Unknown FILE type");
        }

        return SingletonValue.VOID;
    }

    public static SingletonValue atspre_print_int(Integer x) {
        return atspre_fprint_int(Ptrk.c_stdout, x);
    }

    public static SingletonValue atspre_prerr_int(Integer x) {
        return atspre_fprint_int(Ptrk.c_stderr, x);
    }

    // ===================================================
    
    public static Integer atspre_g0uint_succ_size(Integer x) {
        return x + 1;
    }
    
    public static Integer atspre_g0uint_pred_size(Integer x) {
        return x - 1;
    }
    
    public static Integer atspre_g0uint_half_size(Integer x) {
        return x / 2;
    }
    
    public static Integer atspre_g0uint_add_size(Integer x1, Integer x2) {
        return x1 / 2;
    }

    public static Integer atspre_g0uint_sub_size(Integer x1, Integer x2) {
        return x1 - x2;
    }

    public static Integer atspre_g0uint_mul_size(Integer x1, Integer x2) {
        return x1 * x2;
    }

    public static Integer atspre_g0uint_div_size(Integer x1, Integer x2) {
        return x1 / x2;
    }

    public static Integer atspre_g0uint_mod_size(Integer x1, Integer x2) {
        return x1 % x2;
    }
    
    // ===================================================

    public static Integer atspre_g1uint_succ_size(Integer x) {
        return atspre_g0uint_succ_size(x);
    }
    
    public static Integer atspre_g1uint_pred_size(Integer x) {
        return atspre_g0uint_pred_size(x);
    }
    
    public static Integer atspre_g1uint_half_size(Integer x) {
        return atspre_g0uint_half_size(x);
    }
    
    public static Integer atspre_g1uint_add_size(Integer x1, Integer x2) {
        return atspre_g0uint_add_size(x1, x2);
    }

    public static Integer atspre_g1uint_sub_size(Integer x1, Integer x2) {
        return atspre_g0uint_sub_size(x1, x2);
    }

    public static Integer atspre_g1uint_mul_size(Integer x1, Integer x2) {
        return atspre_g0uint_mul_size(x1, x2);
    }

    public static Integer atspre_g1uint_div_size(Integer x1, Integer x2) {
        return atspre_g0uint_div_size(x1, x2);
    }

    public static Integer atspre_g1uint_mod_size(Integer x1, Integer x2) {
        return atspre_g0uint_mod_size(x1, x2);
    }

    // ===================================================

    public static Boolean atspre_g1int_gt_int(Integer x1, Integer x2) {
        return atspre_g0int_gt_int(x1, x2);
    }

    public static Boolean atspre_g1int_gte_int(Integer x1, Integer x2) {
        return atspre_g0int_gte_int(x1, x2);
    }

    public static Boolean atspre_g1int_eq_int(Integer x1, Integer x2) {
        return atspre_g0int_eq_int(x1, x2);
    }

    public static Boolean atspre_g1int_neq_int(Integer x1, Integer x2) {
        return atspre_g0int_neq_int(x1, x2);
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

    public static Boolean atspre_g0uint_lt_size(Integer x1, Integer x2) {
        return x1 < x2;
    }

    public static Boolean atspre_g0uint_lte_size(Integer x1, Integer x2) {
        return x1 <= x2;
    }

    public static Boolean atspre_g0uint_gt_size(Integer x1, Integer x2) {
        return x1 > x2;
    }

    public static Boolean atspre_g0uint_gte_size(Integer x1, Integer x2) {
        return x1 >= x2;
    }

    public static Boolean atspre_g0uint_eq_size(Integer x1, Integer x2) {
        return x1 == x2;
    }

    public static Boolean atspre_g0uint_neq_size(Integer x1, Integer x2) {
        return x1 != x2;
    }

    // ===================================================

    public static Boolean atspre_g1uint_lt_size(Integer x1, Integer x2) {
        return atspre_g0uint_lt_size(x1, x2);
    }

    public static Boolean atspre_g1uint_lte_size(Integer x1, Integer x2) {
        return atspre_g0uint_lte_size(x1, x2);
    }

    public static Boolean atspre_g1uint_gt_size(Integer x1, Integer x2) {
        return atspre_g0uint_gt_size(x1, x2);
    }

    public static Boolean atspre_g1uint_gte_size(Integer x1, Integer x2) {
        return atspre_g0uint_gte_size(x1, x2);
    }

    public static Boolean atspre_g1uint_eq_size(Integer x1, Integer x2) {
        return atspre_g0uint_eq_size(x1, x2);
    }

    public static Boolean atspre_g1uint_neq_size(Integer x1, Integer x2) {
        return atspre_g0uint_neq_size(x1, x2);
    }

    // ===================================================

    public static Integer atspre_g0uint2uint_size_size(Integer x) {
        return x;
    }

    // ===================================================

    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);

        typscope.addValue("atspre_g0int2int_int_int", intFunc);
        
        typscope.addValue("atspre_g1int2int_int_int", intFunc);
        
        typscope.addValue("atspre_g0int2uint_int_size", sizeFunc);
        
        typscope.addValue("atspre_g1int2uint_int_size", sizeFunc);

        typscope.addValue("atspre_g0int_gt_int", boolFunc);
        typscope.addValue("atspre_g0int_gte_int", boolFunc);
        typscope.addValue("atspre_g0int_eq_int", boolFunc);
        typscope.addValue("atspre_g0int_neq_int", boolFunc);
        typscope.addValue("atspre_g0int_lt_int", boolFunc);
        typscope.addValue("atspre_g0int_lte_int", boolFunc);

        typscope.addValue("atspre_g1int_gt_int", boolFunc);
        typscope.addValue("atspre_g1int_gte_int", boolFunc);
        typscope.addValue("atspre_g1int_eq_int", boolFunc);
        typscope.addValue("atspre_g1int_neq_int", boolFunc);
        typscope.addValue("atspre_g1int_lt_int", boolFunc);
        typscope.addValue("atspre_g1int_lte_int", boolFunc);
        
        typscope.addValue("atspre_g0int_add_int", intFunc);
        typscope.addValue("atspre_g0int_sub_int", intFunc);
        typscope.addValue("atspre_g0int_mul_int", intFunc);
        typscope.addValue("atspre_g0int_div_int", intFunc);

        typscope.addValue("atspre_g1int_add_int", intFunc);
        typscope.addValue("atspre_g1int_sub_int", intFunc);
        typscope.addValue("atspre_g1int_mul_int", intFunc);
        typscope.addValue("atspre_g1int_div_int", intFunc);
        
        typscope.addValue("atspre_fprint_int", voidFunc);
        typscope.addValue("atspre_print_int", voidFunc);
        typscope.addValue("atspre_prerr_int", voidFunc);

        
        typscope.addValue("atspre_g0uint_succ_size", sizeFunc);
        typscope.addValue("atspre_g0uint_pred_size", sizeFunc);
        typscope.addValue("atspre_g0uint_half_size", sizeFunc);
        typscope.addValue("atspre_g0uint_add_size", sizeFunc);
        typscope.addValue("atspre_g0uint_sub_size", sizeFunc);
        typscope.addValue("atspre_g0uint_mul_size", sizeFunc);
        typscope.addValue("atspre_g0uint_div_size", sizeFunc);
        typscope.addValue("atspre_g0uint_mod_size", sizeFunc);
        
        typscope.addValue("atspre_g1uint_succ_size", sizeFunc);
        typscope.addValue("atspre_g1uint_pred_size", sizeFunc);
        typscope.addValue("atspre_g1uint_half_size", sizeFunc);
        typscope.addValue("atspre_g1uint_add_size", sizeFunc);
        typscope.addValue("atspre_g1uint_sub_size", sizeFunc);
        typscope.addValue("atspre_g1uint_mul_size", sizeFunc);
        typscope.addValue("atspre_g1uint_div_size", sizeFunc);
        typscope.addValue("atspre_g1uint_mod_size", sizeFunc);

        typscope.addValue("atspre_g0uint_gt_size", boolFunc);
        typscope.addValue("atspre_g0uint_gte_size", boolFunc);
        typscope.addValue("atspre_g0uint_eq_size", boolFunc);
        typscope.addValue("atspre_g0uint_neq_size", boolFunc);
        typscope.addValue("atspre_g0uint_lt_size", boolFunc);
        typscope.addValue("atspre_g0uint_lte_size", boolFunc);

        typscope.addValue("atspre_g1uint_gt_size", boolFunc);
        typscope.addValue("atspre_g1uint_gte_size", boolFunc);
        typscope.addValue("atspre_g1int_eq_size", boolFunc);
        typscope.addValue("atspre_g1uint_neq_size", boolFunc);
        typscope.addValue("atspre_g1uint_lt_size", boolFunc);
        typscope.addValue("atspre_g1uint_lte_size", boolFunc);

        typscope.addValue("atspre_g0uint2uint_size_size", intFunc);
    }

    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }
}

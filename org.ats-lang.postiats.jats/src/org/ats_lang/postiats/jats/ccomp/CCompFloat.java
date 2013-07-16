package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.*;

public class CCompFloat {

    public static Double atspre_mul_int_double(Integer i1, Double f2) {
        return i1 * f2;
    }

    public static Double atspre_g0int2float_int_double(Integer x) {
        return new Double(x);
    }

    public static Double atspre_g0float_add_double(Double x1, Double x2) {
        return x1 + x2;
    }

    public static Double atspre_g0float_sub_double(Double x1, Double x2) {
        return x1 - x2;
    }

    public static Double atspre_g0float_mul_double(Double x1, Double x2) {
        return x1 * x2;
    }

    public static Double atspre_g0float_div_double(Double x1, Double x2) {
        return x1 / x2;
    }

    // ===================================================
    
    public static SingletonValue atspre_fprint_double(Ptrk out, Double x) {
        if (out == Ptrk.c_stderr) {
            System.err.print(x);
        } else if (out == Ptrk.c_stdout) {
            System.out.print(x);
        } else {
            throw new Error("Unknown FILE type");
        }

        return SingletonValue.VOID;
    }

    public static SingletonValue atspre_print_double(Double x) {
        return atspre_fprint_double(Ptrk.c_stdout, x);
    }

    public static SingletonValue atspre_prerr_double(Double x) {
        return atspre_fprint_double(Ptrk.c_stderr, x);
    }

    // ===================================================

    public static Integer atspre_g0float_compare_double(Double x1, Double x2) {
        if (x1 < x2) {
            return -1;
        } else if (x1 > x2) {
            return 1;
        } else {
            return 0;
        }
    }
    // ===================================================

    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType, null);
        FuncType sizeFunc = new FuncType(SizeType.cType, null);
        FuncType boolFunc = new FuncType(BoolType.cType, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType doubleFunc = new FuncType(DoubleType.cType, null);

        typscope.addValue("atspre_mul_int_double", doubleFunc);
        typscope.addValue("atspre_g0int2float_int_double", doubleFunc);
        typscope.addValue("atspre_g0float_add_double", doubleFunc);
        typscope.addValue("atspre_g0float_sub_double", doubleFunc);
        typscope.addValue("atspre_g0float_mul_double", doubleFunc);
        typscope.addValue("atspre_g0float_div_double", doubleFunc);
        typscope.addValue("atspre_fprint_double", voidFunc);
        typscope.addValue("atspre_print_double", voidFunc);
        typscope.addValue("atspre_prerr_double", voidFunc);
        
        typscope.addValue("atspre_g0float_compare_double", intFunc);
    }

    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }

}

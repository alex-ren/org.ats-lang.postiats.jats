package org.ats_lang.postiats.jats.ccomp;


import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.*;

public class CCompFloat {

    public static SingletonValue atspre_print_double(Double x) {
        System.out.print(x);
        
        return SingletonValue.VOID;
        
    }
    
    public static Double atspre_mul_int_double(Integer i1, Double f2) {
        return i1 * f2;
    }

    public static Double  atspre_g0int2float_int_double(Integer x) {
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
    
    static public void populateFuncType(ATSScope<Object> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType strFunc = new FuncType(StringType.cType, null);
        FuncType sarrptrFunc = new FuncType(StringType.cType, null);
        FuncType doubleFunc = new FuncType(DoubleType.cType0, null);

        typscope.addValue("atspre_print_double", voidFunc);
        typscope.addValue("atspre_mul_int_double", doubleFunc);
        typscope.addValue("atspre_g0int2float_int_double", doubleFunc);
        typscope.addValue("atspre_g0float_add_double", doubleFunc);
        typscope.addValue("atspre_g0float_sub_double", doubleFunc);
        typscope.addValue("atspre_g0float_mul_double", doubleFunc);
        typscope.addValue("atspre_g0float_div_double", doubleFunc);
    }
    
}

package org.ats_lang.postiats.jats.ccomp;


import org.ats_lang.postiats.jats.value.*;

public class CCompFloat {

    public static SingletonValue atspre_print_double(DoubleValue x) {
        Double i = x.getContent();
        System.out.print(i);
        
        return SingletonValue.VOID;
        
    }
    
    public static DoubleValue atspre_mul_int_double(IntValue i1, DoubleValue f2) {
        Integer v1 = i1.getContent();
        Double v2 = f2.getContent();
        Double ret = v1 * v2;
        return new DoubleValue(ret);
    }

    public static DoubleValue  atspre_g0int2float_int_double(IntValue x) {
        return new DoubleValue((double)(x.getContent()));
    }
    
    public static DoubleValue atspre_g0float_add_double(DoubleValue x1, DoubleValue x2) {
        Double d1 = x1.getContent();
        Double d2 = x2.getContent();
        return new DoubleValue(d1 + d2);        
    }
    
    public static DoubleValue atspre_g0float_sub_double(DoubleValue x1, DoubleValue x2) {
        Double d1 = x1.getContent();
        Double d2 = x2.getContent();
        return new DoubleValue(d1 - d2);        
    }
    
    public static DoubleValue atspre_g0float_mul_double(DoubleValue x1, DoubleValue x2) {
        Double d1 = x1.getContent();
        Double d2 = x2.getContent();
        return new DoubleValue(d1 * d2);        
    }
    
    public static DoubleValue atspre_g0float_div_double(DoubleValue x1, DoubleValue x2) {
        Double d1 = x1.getContent();
        Double d2 = x2.getContent();
        return new DoubleValue(d1 / d2);        
    }
    
    
}

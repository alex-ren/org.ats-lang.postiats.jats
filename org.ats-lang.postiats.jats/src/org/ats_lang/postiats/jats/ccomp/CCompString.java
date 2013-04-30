package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class CCompString {

    
    public static SingletonValue atspre_fprint_string(SingletonValue out, char[] x) {
        String str = StringType.toString(x);
        if (out.equals(SingletonValue.c_stderr)) {
            System.err.print(str);
        } else if (out.equals(SingletonValue.c_stdout)) {
            System.out.print(str);
        } else {
            throw new Error("Unknown FILE type");
        }
        
        return SingletonValue.VOID;       
    }
    
    public static SingletonValue atspre_print_string(char[] x) {
        return atspre_fprint_string(SingletonValue.c_stdout, x);
    }
    
    public static SingletonValue atspre_prerr_string(char[] x) {
        return atspre_fprint_string(SingletonValue.c_stderr, x);
    }
    
    static public void populateFuncType(ATSScope<Object> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType strFunc = new FuncType(StringType.cType, null);
        FuncType sarrptrFunc = new FuncType(StringType.cType, null);

        typscope.addValue("atspre_fprint_string", voidFunc);
        typscope.addValue("atspre_print_string", voidFunc);
        typscope.addValue("atspre_prerr_string", voidFunc);
    }
    
}


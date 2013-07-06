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

public class CCompString {

    public static SingletonValue atspre_fprint_string(Ptrk out, Ptrk x) {
        String str = x.createString();
        if (out == Ptrk.c_stderr) {
            System.err.print(str);
        } else if (out == Ptrk.c_stdout) {
            System.out.print(str);
        } else {
            throw new Error("Unknown FILE type");
        }

        return SingletonValue.VOID;
    }

    public static SingletonValue atspre_print_string(Ptrk x) {
        return atspre_fprint_string(Ptrk.c_stdout, x);
    }

    public static SingletonValue atspre_prerr_string(Ptrk x) {
        return atspre_fprint_string(Ptrk.c_stderr, x);
    }


    public static boolean atspre_eq_string_string(Ptrk x1, Ptrk x2) {
        String s1 = x1.createString();
        String s2 = x2.createString();
        return s1.equals(s2);
    }
    
    public static boolean atspre_neq_string_string(Ptrk x1, Ptrk x2) {
        String s1 = x1.createString();
        String s2 = x2.createString();
        return !s1.equals(s2);
    }
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType, null);
        FuncType sizeFunc = new FuncType(SizeType.cType, null);
        FuncType boolFunc = new FuncType(BoolType.cType, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);


        typscope.addValue("atspre_fprint_string", voidFunc);
        typscope.addValue("atspre_print_string", voidFunc);
        typscope.addValue("atspre_prerr_string", voidFunc);
        typscope.addValue("atspre_eq_string_string", boolFunc);
        typscope.addValue("atspre_neq_string_string", boolFunc);
    }
    
    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }

}

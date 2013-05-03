package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.Ptrk.StringElement;

public class CCompBasics {

    
    public static Ptrk atspre_argv_get_at(Ptrk[] argv, Integer i) {
        return argv[i];
        
    }
    
    public static SingletonValue atspre_exit(Integer ecode) {
        System.exit(ecode);
        return SingletonValue.VOID; 
    }
    
    public static SingletonValue atspre_exit_errmsg(Integer ecode, Ptrk msg) {
        System.err.print("exit(ATS): " + StringType.toString(msg));
        System.exit(ecode);
        return SingletonValue.VOID; 
    }
    
    public static SingletonValue atspre_exit_void(Integer ecode) {
        return atspre_exit_void(ecode);
    }
    
    public static SingletonValue atspre_assert_bool(Boolean b) {
        if (!b) {
            System.exit(1);
        }
        return SingletonValue.VOID;
    }
    
    public static SingletonValue atspre_assert_bool0(Boolean b) {
        return atspre_assert_bool(b);
    }
    
    public static SingletonValue atspre_assert_bool1(Boolean b) {
        return atspre_assert_bool(b);
    }

//    atsvoid_t0ype
//    atspre_assert_errmsg_bool
//    (
//      atstype_bool b, atstype_string msg
//    )
    public static SingletonValue atspre_assert_errmsg_bool(Boolean b, Ptrk msg) {
        
        if (b.equals(false)) {
            System.err.print(StringType.toString(msg));
            System.exit(1);
        }
        return SingletonValue.VOID;
    }
    
    public static SingletonValue atspre_assert_errmsg_bool0(Boolean b, Ptrk msg) {
        return atspre_assert_errmsg_bool(b, msg);
    }
    
    public static SingletonValue atspre_assert_errmsg_bool1(Boolean b, Ptrk msg) {
        return atspre_assert_errmsg_bool(b, msg);
    }

    
    public static SingletonValue atspre_fprint_newline(SingletonValue out) {
        if (out == SingletonValue.c_stderr) {
            System.err.println();
        } else if (out == SingletonValue.c_stdout) {
            System.out.println();
        } else {
            throw new Error("Unknown FILE type");
        }
        
        return SingletonValue.VOID;       
    }
    
    public static SingletonValue atspre_print_newline() {
        return atspre_fprint_newline(SingletonValue.c_stdout);
    }
    
    public static SingletonValue atspre_prerr_newline() {
        return atspre_fprint_newline(SingletonValue.c_stderr);
    }
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType ptrkFunc = new FuncType(PtrkType.cType, null);
        

        typscope.addValue("atspre_argv_get_at", ptrkFunc);
        typscope.addValue("atspre_exit", voidFunc);
        typscope.addValue("atspre_exit_errmsg", voidFunc);
        typscope.addValue("atspre_exit_void", voidFunc);
        typscope.addValue("atspre_assert_bool", voidFunc);
        typscope.addValue("atspre_assert_bool0", voidFunc);
        typscope.addValue("atspre_assert_bool1", voidFunc);
        typscope.addValue("atspre_assert_errmsg_bool", voidFunc);
        typscope.addValue("atspre_assert_errmsg_bool0", voidFunc);
        typscope.addValue("atspre_assert_errmsg_bool1", voidFunc);
        typscope.addValue("atspre_fprint_newline", voidFunc);
        typscope.addValue("atspre_print_newline", voidFunc);
        typscope.addValue("atspre_prerr_newline", voidFunc);
        
        
    }
}

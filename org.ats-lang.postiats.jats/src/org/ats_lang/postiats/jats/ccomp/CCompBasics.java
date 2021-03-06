package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class CCompBasics {

    
    public static Ptrk atspre_argv_get_at(Ptrk argv, Integer i) {
        Ptrk ret = argv.SelArrInd(i, PtrkType.cType);
        return (Ptrk)ret.getValue(PtrkType.cType);
        
    }
    
    public static SingletonValue atspre_exit(Integer ecode) {
        System.exit(ecode);
        return SingletonValue.VOID; 
    }
    
    public static SingletonValue atspre_exit_errmsg(Integer ecode, Ptrk msg) {
        System.err.print("exit(ATS): " + msg.createString());
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
            System.err.print(msg.createString());
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

    
    public static SingletonValue atspre_fprint_newline(Ptrk out) {
        if (out == Ptrk.c_stderr) {
            System.err.println();
        } else if (out == Ptrk.c_stdout) {
            System.out.println();
        } else {
            throw new Error("Unknown FILE type");
        }
        
        return SingletonValue.VOID;       
    }
    
    public static SingletonValue atspre_print_newline() {
        return atspre_fprint_newline(Ptrk.c_stdout);
    }
    
    public static SingletonValue atspre_prerr_newline() {
        return atspre_fprint_newline(Ptrk.c_stderr);
    }
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType, null);
        FuncType sizeFunc = new FuncType(SizeType.cType, null);
        FuncType boolFunc = new FuncType(BoolType.cType, null);
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
    
    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }
    
    
}


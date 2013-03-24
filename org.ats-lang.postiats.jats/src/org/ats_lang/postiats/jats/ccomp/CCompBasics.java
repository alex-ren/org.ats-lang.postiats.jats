package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.StringValue;

public class CCompBasics {

    
    public static StringValue atspre_argv_get_at(PtrValue argv, IntValue i) {
        return (StringValue)argv.deRef(i.getContent());
        
    }
    
    public static SingletonValue atspre_exit(IntValue ecode) {
        System.exit(ecode.getContent());
        return SingletonValue.VOID; 
    }
    
    public static SingletonValue atspre_exit_errmsg(IntValue ecode, StringValue msg) {
        System.err.print("exit(ATS): " + msg.getContent());
        System.exit(ecode.getContent());
        return SingletonValue.VOID; 
    }
    
    public static SingletonValue atspre_exit_void(IntValue ecode) {
        return atspre_exit_void(ecode);
    }
    
    public static SingletonValue atspre_assert_bool(BoolValue b) {
        if (!b.isTrue()) {
            System.exit(1);
        }
        return SingletonValue.VOID;
    }
    
    public static SingletonValue atspre_assert_bool0(BoolValue b) {
        return atspre_assert_bool(b);
    }
    
    public static SingletonValue atspre_assert_bool1(BoolValue b) {
        return atspre_assert_bool(b);
    }

    public static SingletonValue atspre_assert_errmsg_bool(BoolValue b, StringValue msg) {
        if (!b.isTrue()) {
            System.err.print(msg.getContent());
            System.exit(1);
        }
        return SingletonValue.VOID;
    }
    
    public static SingletonValue atspre_assert_errmsg_bool0(BoolValue b, StringValue msg) {
        return atspre_assert_errmsg_bool(b, msg);
    }
    
    public static SingletonValue atspre_assert_errmsg_bool1(BoolValue b, StringValue msg) {
        return atspre_assert_errmsg_bool(b, msg);
    }

    
    public static SingletonValue atspre_fprint_newline(PtrValue out) {
        if (out == PtrValue.c_stderr) {
            System.err.println();
        } else if (out == PtrValue.c_stdout) {
            System.out.println();
        } else {
            throw new Error("Unknown FILE type");
        }
        
        return SingletonValue.VOID;       
    }
    
    public static SingletonValue atspre_print_newline() {
        return atspre_fprint_newline(PtrValue.c_stdout);
    }
    
    public static SingletonValue atspre_prerr_newline() {
        return atspre_fprint_newline(PtrValue.c_stderr);
    }
    
}

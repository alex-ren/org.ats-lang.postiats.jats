package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;



public class CCompChar {
    
 // ===================================================
    
    public static boolean atspre_eq_char_char(char x1, char x2) {
        return x1 == x2;
    }
    
    public static boolean atspre_eq_char0_char0(char x1, char x2) {
        return atspre_eq_char_char(x1, x2);
    }
    
    public static boolean atspre_eq_char1_char1(char x1, char x2) {
        return atspre_eq_char_char(x1, x2);
    }
    
    public static boolean atspre_neq_char_char(char x1, char x2) {
        return x1 != x2;
        
    }
    
    public static boolean atspre_neq_char0_char0(char x1, char x2) {
        return atspre_neq_char_char(x1, x2);
    }
    
    public static boolean atspre_neq_char1_char1(char x1, char x2) {
        return atspre_neq_char_char(x1, x2);
    }
    
    public static boolean atspre_isdigit_char(char c) {
        return Character.isDigit(c);
        
    }
    
    public static Integer atspre_sub_char_char(char c1, char c2) {
        int ret = c1 - c2;
        return ret;
    }
    
    public static Integer atspre_sub_char0_char0(char c1, char c2) {
        return atspre_sub_char_char(c1, c2);
    }
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType0, null);
        FuncType sizeFunc = new FuncType(SizeType.cType0, null);
        FuncType boolFunc = new FuncType(BoolType.cType0, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);

        typscope.addValue("atspre_eq_char_char", boolFunc);
        typscope.addValue("atspre_eq_char0_char0", boolFunc);
        typscope.addValue("atspre_eq_char1_char1", boolFunc);
        typscope.addValue("atspre_neq_char_char", boolFunc);
        typscope.addValue("atspre_neq_char0_char0", boolFunc);
        typscope.addValue("atspre_neq_char1_char1", boolFunc);
        typscope.addValue("atspre_isdigit_char", boolFunc);
        typscope.addValue("atspre_sub_char_char", intFunc);
        typscope.addValue("atspre_sub_char0_char0", intFunc);
    }
    
    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }
    

}

package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.CharValue;
import org.ats_lang.postiats.jats.value.IntValue;


public class CCompChar {
    
 // ===================================================
    
    public static BoolValue atspre_eq_char_char(CharValue x1, CharValue x2) {
        Character i1 = x1.getContent();
        Character i2 = x2.getContent();
        
        return new BoolValue(i1 == i2);
        
    }
    
    public static BoolValue atspre_eq_char0_char0(CharValue x1, CharValue x2) {
        return atspre_eq_char_char(x1, x2);
    }
    
    public static BoolValue atspre_eq_char1_char1(CharValue x1, CharValue x2) {
        return atspre_eq_char_char(x1, x2);
    }
    
    public static BoolValue atspre_neq_char_char(CharValue x1, CharValue x2) {
        Character i1 = x1.getContent();
        Character i2 = x2.getContent();
        
        return new BoolValue(i1 != i2);
        
    }
    
    public static BoolValue atspre_neq_char0_char0(CharValue x1, CharValue x2) {
        return atspre_neq_char_char(x1, x2);
    }
    
    public static BoolValue atspre_neq_char1_char1(CharValue x1, CharValue x2) {
        return atspre_neq_char_char(x1, x2);
    }
    
    public static BoolValue atspre_isdigit_char(CharValue c) {
        Character ch = c.getContent();
        return new BoolValue(Character.isDigit(ch));
        
    }
    
    public static IntValue atspre_sub_char_char(CharValue c1, CharValue c2) {
        Character x1 = c1.getContent();
        Character x2 = c2.getContent();
        int ret = x1 - x2;
        return new IntValue(ret);
    }
    
    public static IntValue atspre_sub_char0_char0(CharValue c1, CharValue c2) {
        return atspre_sub_char_char(c1, c2);
    }
    

}

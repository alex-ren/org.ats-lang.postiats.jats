package org.ats_lang.postiats.jats.ccomp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.LibFunc;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.StringValue;


public class CCompFuncs {

    public static Map<String, FuncDef> getLibFuncs() {
        Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
     
        
        LibFunc atspre_print_string = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                String str = ((StringValue)iter.next()).getContent();
                System.out.print(str);
                
                return SingletonValue.VOID;
            }
        };
        
        funcs.put("atspre_print_string", atspre_print_string);
        
        LibFunc atspre_g0int2int_int_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int2int_int_int(iter.next());
            }
        };
        
        funcs.put("atspre_g0int2int_int_int", atspre_g0int2int_int_int);
        
        LibFunc atspre_g0int_gt_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int_gt_int((IntValue)iter.next(), (IntValue)iter.next());
            }
        };
        
        funcs.put("atspre_g0int_gt_int", atspre_g0int_gt_int);
        
        
        
        
        
        
        
        
        
        
        return funcs;
    }
        
}
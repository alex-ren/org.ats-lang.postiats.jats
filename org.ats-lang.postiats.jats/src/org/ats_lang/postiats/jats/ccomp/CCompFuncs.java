package org.ats_lang.postiats.jats.ccomp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.LibFunc;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SizeValue;
import org.ats_lang.postiats.jats.value.StringValue;
import org.ats_lang.postiats.jats.value.StructValue;


public class CCompFuncs {

    public static Map<String, FuncDef> getLibFuncs() {
        Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
     
        LibFunc atspre_print_string = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompString.atspre_print_string((StringValue)iter.next());
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
        funcs.put("atspre_g1int2int_int_int", atspre_g0int2int_int_int);
        
        
        LibFunc atspre_g0int_gt_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int_gt_int((IntValue)iter.next(), (IntValue)iter.next());
            }
        };
        
        funcs.put("atspre_g0int_gt_int", atspre_g0int_gt_int);
        funcs.put("atspre_g1int_gt_int", atspre_g0int_gt_int);
        
        
        LibFunc atspre_g0int_add_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int_add_int((IntValue)iter.next(), (IntValue)iter.next());
            }
        };
        
        funcs.put("atspre_g0int_add_int", atspre_g0int_add_int);
        funcs.put("atspre_g1int_add_int", atspre_g0int_add_int);
        
        LibFunc atspre_g0int_sub_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int_sub_int((IntValue)iter.next(), (IntValue)iter.next());
            }
        };
        
        funcs.put("atspre_g0int_sub_int", atspre_g0int_sub_int);
        funcs.put("atspre_g1int_sub_int", atspre_g0int_sub_int);
        
        LibFunc atspre_g0int_mul_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int_mul_int((IntValue)iter.next(), (IntValue)iter.next());
            }
        };
        
        funcs.put("atspre_g0int_mul_int", atspre_g0int_mul_int);
        funcs.put("atspre_g1int_mul_int", atspre_g0int_mul_int);
        
        LibFunc atspre_g0int_div_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int_div_int((IntValue)iter.next(), (IntValue)iter.next());
            }
        };
        
        funcs.put("atspre_g0int_div_int", atspre_g0int_div_int);
        funcs.put("atspre_g1int_div_int", atspre_g0int_div_int);
        
        LibFunc atspre_print_int = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_print_int((IntValue)iter.next());
            }
        };
        
        funcs.put("atspre_print_int", atspre_print_int);
        
        LibFunc atspre_print_newline = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                return CCompBasics.atspre_print_newline();
            }
        };
        
        funcs.put("atspre_print_newline", atspre_print_newline);
        
        LibFunc atspre_arrpsz_get_ptrsize = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompArrayPtr.atspre_arrpsz_get_ptrsize((StructValue)iter.next(), (PtrValue)iter.next());
            }
        };
        
        funcs.put("atspre_arrpsz_get_ptrsize", atspre_arrpsz_get_ptrsize);
        
        
        LibFunc atspre_g0int2uint_int_size = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0int2uint_int_size(iter.next());
            }
        };
        
        funcs.put("atspre_g0int2uint_int_size", atspre_g0int2uint_int_size);
        
        LibFunc atspre_g0uint_mul_size = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompInteger.atspre_g0uint_mul_size((SizeValue)iter.next(), (SizeValue)iter.next());
            }
        };
        
        funcs.put("atspre_g0uint_mul_size", atspre_g0uint_mul_size);
        
        LibFunc atspre_ptr0_add_bytesize = new LibFunc() {
            public ATSValue evaluate(List<ATSValue> args) {
                Iterator<ATSValue> iter = args.iterator();
                return CCompArrayPtr.atspre_ptr0_add_bytesize((PtrValue)iter.next(), (SizeValue)iter.next());
            }
        };
        
        funcs.put("atspre_ptr0_add_bytesize", atspre_ptr0_add_bytesize);
        
        
        
        return funcs;
    }
        
}
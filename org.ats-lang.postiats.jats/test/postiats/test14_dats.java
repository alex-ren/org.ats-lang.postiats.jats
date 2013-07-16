
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test14_dats {
    /*UserFunc*/static public SingletonValue srand48_with_time_2() {
        /*BlockNode*//*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSCNTRB_056$atshwxi_056$testing_randgen_arrayptr$6$1(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp15$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp16$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp16$1 = /*FuncCallNode*/ATSLIB_056$prelude_arrayptr_make_uninitized$9$1(arg0);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_array_initize$14$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/tmp16$1, arg0);
        /*AtsInsMove*/tmp15$1 = tmp16$1;
        /*AtsReturn*/return tmp15$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_arrayptr_make_uninitized$9$1(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp22$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp23$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp23$1 = /*FuncCallNode*/ATSLIB_056$prelude_array_ptr_alloc$11$1(arg0);
        /*AtsInsMove*/tmp22$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp23$1;
        /*AtsReturn*/return tmp22$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_array_ptr_alloc$11$1(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp26$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp27$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp28$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp28$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_mul_size(arg0, /*AtsPmvSizeofNode*/8);
        /*AtsInsMove*/tmp27$1 = /*FuncCallNode*/CCompMemory.atspre_malloc_gc(tmp28$1);
        /*AtsInsMove*/tmp26$1 = tmp27$1;
        /*AtsReturn*/return tmp26$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_array_initize$14$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp40$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp40$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(0);
        /*AtsInsMoveVoid*//*FuncCallNode*/loop_15$15$1(arg0, arg1, tmp40$1);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue loop_15$15$1(Ptrk arg0, Integer arg1, Integer arg2) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp34$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp37$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp38$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp39$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp34$1 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g1uint_int$18$1(arg1, 0);
        /*IfNode*/if (tmp34$1 == true) {
            /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_array_initize_044$init$7$1(arg2, arg0);
            /*AtsInsMove*/tmp37$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_succ$25$1(arg0);
            /*AtsInsMove*/tmp38$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_pred_size(arg1);
            /*AtsInsMove*/tmp39$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint_succ_size(arg2);
            /*AtsInsMoveVoid*//*FuncCallNode*/loop_15$15$1(tmp37$1, tmp38$1, tmp39$1);
            /*AtsInsMoveVoid*/
        } else {
            /*BlockNode*//*AtsInsMoveVoid*/
        }
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g1uint_int$18$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp50$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp51$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp51$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2uint_int_size(arg1);
        /*AtsInsMove*/tmp50$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_gt_size(arg0, tmp51$1);
        /*AtsReturn*/return tmp50$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_array_initize_044$init$7$1(Integer arg0, Ptrk arg1) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSCNTRB_056$atshwxi_056$testing_randgen_ref$22$1(arg1);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSCNTRB_056$atshwxi_056$testing_randgen_ref$22$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Double tmp56$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp56$1 = /*FuncCallNode*/ATSCNTRB_056$atshwxi_056$testing_randgen_val$3$1();
        /*AtsInsStore*/RefType.updateFromNonRefType(arg0, tmp56$1, DoubleType.cType);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Double ATSCNTRB_056$atshwxi_056$testing_randgen_val$3$1() {
        /*BlockNode*//*DefinitionNode*/Double tmp10$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp10$1 = /*FuncCallNode*/CCompStdlib.atslib_drand48();
        /*AtsReturn*/return tmp10$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_succ$25$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp60$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp61$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp61$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_succ$27$1(arg0);
        /*AtsInsMove*/tmp60$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp61$1;
        /*AtsReturn*/return tmp60$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_succ$27$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp64$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp64$1 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, /*AtsPmvSizeofNode*/8);
        /*AtsReturn*/return tmp64$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$1 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$1();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$1, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$1() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$1 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_arrayptr$37$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array$39$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array$39$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp84$1 = /*DefinitionNode*/null;
        /*AtsInsMoveVoid*/
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array_044$beg$42$1();
        /*AtsInsMove*/tmp84$1 = /*FuncCallNode*/ATSLIB_056$prelude_array_iforeach$46$1(arg0, arg1);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array_044$end$72$1();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array_044$beg$42$1() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$2(StringType.fromString("("));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$2 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$2();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$2, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$2() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$2 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$2;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_array_iforeach$46$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp96$1 = /*DefinitionNode*/null;
        /*AtsInsMoveVoid*/
        /*AtsInsMove*/tmp96$1 = /*FuncCallNode*/ATSLIB_056$prelude_array_iforeach_env$48$1(arg0, arg1, /*AtsPmvPtrofVoid*/null);
        /*AtsReturn*/return tmp96$1;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_array_iforeach_env$48$1(Ptrk arg0, Integer arg1, Ptrk arg2) {
        /*BlockNode*//*DefinitionNode*/Integer tmp100$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp109$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp110$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp111$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp109$1 = arg0;
        /*AtsInsMove*/tmp111$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(0);
        /*AtsInsMove*/tmp110$1 = /*FuncCallNode*/loop_49$49$1(tmp111$1, tmp109$1, arg1, arg2);
        /*AtsInsMove*/tmp100$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_sub_size(arg1, tmp110$1);
        /*AtsReturn*/return tmp100$1;
    }

    /*UserFunc*/static public Integer loop_49$49$1(Integer arg0, Ptrk arg1, Integer arg2, Ptrk arg3) {
        /*BlockNode*//*DefinitionNode*/Integer tmp101$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp102$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp103$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp105$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp106$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp107$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp108$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp102$1 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g1uint_int$18$2(arg2, 0);
        /*IfNode*/if (tmp102$1 == true) {
            /*BlockNode*//*AtsInsMove*/tmp103$1 = /*FuncCallNode*/ATSLIB_056$prelude_array_iforeach_044$cont$53$1(arg0, arg1, arg3);
            /*IfNode*/if (tmp103$1 == true) {
                /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_array_iforeach_044$fwork$40$1(arg0, arg1, arg3);
                /*AtsInsMove*/tmp106$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint_succ_size(arg0);
                /*AtsInsMove*/tmp107$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_succ$25$2(arg1);
                /*AtsInsMove*/tmp108$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_pred_size(arg2);
                /*AtsInsMove*/tmp105$1 = /*FuncCallNode*/loop_49$49$1(tmp106$1, tmp107$1, tmp108$1, arg3);
                /*AtsInsMove*/tmp101$1 = tmp105$1;
            } else {
                /*BlockNode*//*AtsInsMove*/tmp101$1 = arg2;
            }
        } else {
            /*BlockNode*//*AtsInsMove*/tmp101$1 = arg2;
        }
        /*AtsReturn*/return tmp101$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g1uint_int$18$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp50$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp51$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp51$2 = /*FuncCallNode*/CCompInteger.atspre_g1int2uint_int_size(arg1);
        /*AtsInsMove*/tmp50$2 = /*FuncCallNode*/CCompInteger.atspre_g1uint_gt_size(arg0, tmp51$2);
        /*AtsReturn*/return tmp50$2;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_array_iforeach_044$cont$53$1(Integer arg0, Ptrk arg1, Ptrk arg2) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp126$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp126$1 = true;
        /*AtsReturn*/return tmp126$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_array_iforeach_044$fwork$40$1(Integer arg0, Ptrk arg1, Ptrk arg2) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp81$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp81$1 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g0uint_int$56$1(arg0, 0);
        /*IfNode*/if (tmp81$1 == true) {
            /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array_044$sep$59$1();
        } else {
            /*BlockNode*//*AtsInsMoveVoid*/
        }
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_val$63$1((Double)RefType.cloneValue(arg1, DoubleType.cType));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g0uint_int$56$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp131$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp132$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp132$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp131$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint_gt_size(arg0, tmp132$1);
        /*AtsReturn*/return tmp131$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array_044$sep$59$1() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$3(StringType.fromString(", "));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$3(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$3 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$3();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$3, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$3() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$3 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$3;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_val$63$1(Double arg0) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_double$65$1(arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_double$65$1(Double arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp143$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp143$1 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$4();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompFloat.atspre_fprint_double(tmp143$1, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$4() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$4 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$4 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$4;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_succ$25$2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp60$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp61$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp61$2 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_succ$27$2(arg0);
        /*AtsInsMove*/tmp60$2 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp61$2;
        /*AtsReturn*/return tmp60$2;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_succ$27$2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp64$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp64$2 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, /*AtsPmvSizeofNode*/8);
        /*AtsReturn*/return tmp64$2;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array_044$end$72$1() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$4(StringType.fromString(")"));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$4(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$4 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$4 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$5();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$4, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$5() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$5 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$5 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$5;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_newline$76$1() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp157$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp157$1 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$6();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_fprint_newline(tmp157$1);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$6() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$6 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$6 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$6;
    }

    /*UserFunc*/static public SingletonValue revarr_0$0$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp8$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp6$1 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g1uint_int$18$3(arg1, 0);
        /*IfNode*/if (tmp6$1 == true) {
            /*BlockNode*//*AtsInsMove*/tmp7$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2uint_int_size(0);
            /*AtsInsMove*/tmp8$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_pred_size(arg1);
            /*AtsInsMoveVoid*//*FuncCallNode*/loop_1$1$1(arg0, tmp7$1, tmp8$1);
        } else {
            /*BlockNode*//*AtsInsMoveVoid*/
        }
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue loop_1$1$1(Ptrk arg0, Integer arg1, Integer arg2) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp2$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp3$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp4$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp5$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp2$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_lt_size(arg1, arg2);
        /*IfNode*/if (tmp2$1 == true) {
            /*BlockNode*//*AtsInsXStore*/tmp3$1 = (Double)RefType.cloneValue(/*AtsSelArrPtrInd*/arg0.SelArrInd(arg1, DoubleType.cType), DoubleType.cType);
            RefType.updateFromRefType(/*AtsSelArrPtrInd*/arg0.SelArrInd(arg1, DoubleType.cType), /*AtsSelArrPtrInd*/arg0.SelArrInd(arg2, DoubleType.cType), DoubleType.cType);
            RefType.updateFromNonRefType(/*AtsSelArrPtrInd*/arg0.SelArrInd(arg2, DoubleType.cType), tmp3$1, DoubleType.cType);
            /*AtsInsMove*/tmp4$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_succ_size(arg1);
            /*AtsInsMove*/tmp5$1 = /*FuncCallNode*/CCompInteger.atspre_g1uint_pred_size(arg2);
            /*AtsInsMoveVoid*//*FuncCallNode*/loop_1$1$1(arg0, tmp4$1, tmp5$1);
        } else {
            /*BlockNode*//*AtsInsMoveVoid*/
        }
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g1uint_int$18$3(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp50$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp51$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp51$3 = /*FuncCallNode*/CCompInteger.atspre_g1int2uint_int_size(arg1);
        /*AtsInsMove*/tmp50$3 = /*FuncCallNode*/CCompInteger.atspre_g1uint_gt_size(arg0, tmp51$3);
        /*AtsReturn*/return tmp50$3;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$5(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$5 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$5 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$7();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$5, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$7() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$7 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$7 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$7;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_arrayptr$37$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array$39$2(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array$39$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp84$2 = /*DefinitionNode*/null;
        /*AtsInsMoveVoid*/
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array_044$beg$42$2();
        /*AtsInsMove*/tmp84$2 = /*FuncCallNode*/ATSLIB_056$prelude_array_iforeach$46$2(arg0, arg1);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array_044$end$72$2();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array_044$beg$42$2() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$6(StringType.fromString("("));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$6(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$6 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$6 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$8();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$6, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$8() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$8 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$8 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$8;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_array_iforeach$46$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp96$2 = /*DefinitionNode*/null;
        /*AtsInsMoveVoid*/
        /*AtsInsMove*/tmp96$2 = /*FuncCallNode*/ATSLIB_056$prelude_array_iforeach_env$48$2(arg0, arg1, /*AtsPmvPtrofVoid*/null);
        /*AtsReturn*/return tmp96$2;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_array_iforeach_env$48$2(Ptrk arg0, Integer arg1, Ptrk arg2) {
        /*BlockNode*//*DefinitionNode*/Integer tmp100$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp109$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp110$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp111$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp109$2 = arg0;
        /*AtsInsMove*/tmp111$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(0);
        /*AtsInsMove*/tmp110$2 = /*FuncCallNode*/loop_49$49$2(tmp111$2, tmp109$2, arg1, arg2);
        /*AtsInsMove*/tmp100$2 = /*FuncCallNode*/CCompInteger.atspre_g1uint_sub_size(arg1, tmp110$2);
        /*AtsReturn*/return tmp100$2;
    }

    /*UserFunc*/static public Integer loop_49$49$2(Integer arg0, Ptrk arg1, Integer arg2, Ptrk arg3) {
        /*BlockNode*//*DefinitionNode*/Integer tmp101$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp102$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp103$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp105$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp106$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp107$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp108$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp102$2 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g1uint_int$18$4(arg2, 0);
        /*IfNode*/if (tmp102$2 == true) {
            /*BlockNode*//*AtsInsMove*/tmp103$2 = /*FuncCallNode*/ATSLIB_056$prelude_array_iforeach_044$cont$53$2(arg0, arg1, arg3);
            /*IfNode*/if (tmp103$2 == true) {
                /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_array_iforeach_044$fwork$40$2(arg0, arg1, arg3);
                /*AtsInsMove*/tmp106$2 = /*FuncCallNode*/CCompInteger.atspre_g0uint_succ_size(arg0);
                /*AtsInsMove*/tmp107$2 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_succ$25$3(arg1);
                /*AtsInsMove*/tmp108$2 = /*FuncCallNode*/CCompInteger.atspre_g1uint_pred_size(arg2);
                /*AtsInsMove*/tmp105$2 = /*FuncCallNode*/loop_49$49$2(tmp106$2, tmp107$2, tmp108$2, arg3);
                /*AtsInsMove*/tmp101$2 = tmp105$2;
            } else {
                /*BlockNode*//*AtsInsMove*/tmp101$2 = arg2;
            }
        } else {
            /*BlockNode*//*AtsInsMove*/tmp101$2 = arg2;
        }
        /*AtsReturn*/return tmp101$2;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g1uint_int$18$4(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp50$4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp51$4 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp51$4 = /*FuncCallNode*/CCompInteger.atspre_g1int2uint_int_size(arg1);
        /*AtsInsMove*/tmp50$4 = /*FuncCallNode*/CCompInteger.atspre_g1uint_gt_size(arg0, tmp51$4);
        /*AtsReturn*/return tmp50$4;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_array_iforeach_044$cont$53$2(Integer arg0, Ptrk arg1, Ptrk arg2) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp126$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp126$2 = true;
        /*AtsReturn*/return tmp126$2;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_array_iforeach_044$fwork$40$2(Integer arg0, Ptrk arg1, Ptrk arg2) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp81$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp81$2 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g0uint_int$56$2(arg0, 0);
        /*IfNode*/if (tmp81$2 == true) {
            /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_array_044$sep$59$2();
        } else {
            /*BlockNode*//*AtsInsMoveVoid*/
        }
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_val$63$2((Double)RefType.cloneValue(arg1, DoubleType.cType));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g0uint_int$56$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp131$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp132$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp132$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp131$2 = /*FuncCallNode*/CCompInteger.atspre_g0uint_gt_size(arg0, tmp132$2);
        /*AtsReturn*/return tmp131$2;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array_044$sep$59$2() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$7(StringType.fromString(", "));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$7(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$7 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$7 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$9();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$7, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$9() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$9 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$9 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$9;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_val$63$2(Double arg0) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_double$65$2(arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_double$65$2(Double arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp143$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp143$2 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$10();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompFloat.atspre_fprint_double(tmp143$2, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$10() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$10 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$10 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$10;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_succ$25$3(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp60$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp61$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp61$3 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_succ$27$3(arg0);
        /*AtsInsMove*/tmp60$3 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp61$3;
        /*AtsReturn*/return tmp60$3;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_succ$27$3(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp64$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp64$3 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, /*AtsPmvSizeofNode*/8);
        /*AtsReturn*/return tmp64$3;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_array_044$end$72$2() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$8(StringType.fromString(")"));
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_string$32$8(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp68$8 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp68$8 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$11();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_fprint_string(tmp68$8, arg0);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$11() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$11 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$11 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$11;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_gprint_newline$76$2() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp157$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp157$2 = /*FuncCallNode*/ATSLIB_056$prelude_gprint_044$out$34$12();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_fprint_newline(tmp157$2);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_gprint_044$out$34$12() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp71$12 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp71$12 = CCompFileBas.atspre_FILE_stdout;
        /*AtsReturn*/return tmp71$12;
    }

    /*UserFunc*/static public Integer mainats_argc_argv_int(Integer arg0, Ptrk arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret11 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp14 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12 = /*FuncCallNode*/CCompInteger.atspre_g1int2uint_int_size(10);
        /*AtsInsMoveVoid*//*FuncCallNode*/srand48_with_time_2();
        /*AtsInsMove*/tmp14 = /*FuncCallNode*/ATSCNTRB_056$atshwxi_056$testing_randgen_arrayptr$6$1(tmp12);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$1(StringType.fromString("A(bef) = "));
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_arrayptr$37$1(tmp14, tmp12);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_newline$76$1();
        /*AtsInsMoveVoid*//*FuncCallNode*/revarr_0$0$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/tmp14, tmp12);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_string$32$5(StringType.fromString("A(aft) = "));
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_arrayptr$37$2(tmp14, tmp12);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_gprint_newline$76$2();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompArrayPtr.atspre_arrayptr_free(tmp14);
        /*AtsInsMove*/tmpret11 = 0;
        /*AtsReturn*/return tmpret11;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test14_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test14_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test14_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test14_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test14_056$dats__dynload();
        Integer mainArgc = args.length + 1;

        Ptrk[] arrArgv = new Ptrk[mainArgc];
        arrArgv[0] = StringType.fromString("test14_dats");

        for (int i = 1; i < mainArgc; ++i) {
            arrArgv[i] = StringType.fromString(args[i - 1]);
        }

        ArrayType argvTy = new ArrayType(PtrkType.cType, mainArgc);

        Ptrk mainArgv = Ptrk.createPtrk(argvTy, arrArgv);
        mainats_argc_argv_int(mainArgc, mainArgv);
    }


}


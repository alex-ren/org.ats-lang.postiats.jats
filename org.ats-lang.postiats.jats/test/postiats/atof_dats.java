
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class atof_dats {
    /*UserFunc*/static public Integer ATSLIB_056$prelude_string_foreach_env$3$1(Ptrk arg0, Ptrk arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp9$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp16$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp17$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp16$1 = /*FuncCallNode*/loop_4$4$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMove*/tmp17$1 = /*FuncCallNode*/CCompPointer.atspre_sub_ptr0_ptr0(tmp16$1, /*AtsPmvCastFn*//*castfn_currently_no_op*/arg0);
        /*AtsInsMove*/tmp9$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp17$1;
        /*AtsReturn*/return tmp9$1;
    }

    /*UserFunc*/static public Ptrk loop_4$4$1(Ptrk arg0, Ptrk arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp10$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Character tmp11$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp12$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp13$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp15$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp11$1 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$7$1(arg0);
        /*AtsInsMove*/tmp13$1 = /*FuncCallNode*/CCompChar.atspre_neq_char0_char0(tmp11$1, '\000');
        /*IfNode*/if (tmp13$1 == true) {
            /*BlockNode*//*AtsInsMove*/tmp12$1 = /*FuncCallNode*/ATSLIB_056$prelude_string_foreach_044$cont$1$1(tmp11$1, arg1);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp12$1 = false;
        }
        /*IfNode*/if (tmp12$1 == true) {
            /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_string_foreach_044$fwork$2$1(tmp11$1, arg1);
            /*AtsInsMove*/tmp15$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_succ$12$1(arg0);
            /*AtsInsMove*/tmp10$1 = /*FuncCallNode*/loop_4$4$1(tmp15$1, arg1);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp10$1 = arg0;
        }
        /*AtsReturn*/return tmp10$1;
    }

    /*UserFunc*/static public Character ATSLIB_056$prelude_056$unsafe_ptr0_get$7$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Character tmp27$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Character tmp28$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp28$1 = (Character)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, CharType.cType);
        /*AtsInsMove*/tmp27$1 = tmp28$1;
        /*AtsReturn*/return tmp27$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_string_foreach_044$cont$1$1(Character arg0, Ptrk arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp2$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp2$1 = /*FuncCallNode*/CCompChar.atspre_isdigit_char(arg0);
        /*AtsReturn*/return tmp2$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_string_foreach_044$fwork$2$1(Character arg0, Ptrk arg1) {
        /*BlockNode*//*DefinitionNode*/Double tmp4$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp5$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp6$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp5$1 = /*FuncCallNode*/CCompFloat.atspre_mul_int_double(10, (Double)RefType.cloneValue(arg1, DoubleType.cType));
        /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/CCompChar.atspre_sub_char0_char0(arg0, '0');
        /*AtsInsMove*/tmp6$1 = /*FuncCallNode*/CCompFloat.atspre_g0int2float_int_double(tmp7$1);
        /*AtsInsMove*/tmp4$1 = /*FuncCallNode*/CCompFloat.atspre_g0float_add_double(tmp5$1, tmp6$1);
        /*AtsInsStore*/RefType.updateFromNonRefType(arg1, tmp4$1, DoubleType.cType);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_succ$12$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp37$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp37$1 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, /*AtsPmvSizeofNode*/1);
        /*AtsReturn*/return tmp37$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_add_guint$14$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp40$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp41$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp41$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_add_guint$16$1(arg0, arg1);
        /*AtsInsMove*/tmp40$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp41$1;
        /*AtsReturn*/return tmp40$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_add_guint$16$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp44$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp45$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp46$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp46$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint2uint_size_size(arg1);
        /*AtsInsMove*/tmp45$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint_mul_size(tmp46$1, /*AtsPmvSizeofNode*/1);
        /*AtsInsMove*/tmp44$1 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, tmp45$1);
        /*AtsReturn*/return tmp44$1;
    }

    /*UserFunc*/static public Double _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats_atof(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Double tmpret0 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref1 = /*DefinitionNode*/DoubleType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp8 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp39 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp50 = /*DefinitionNode*/null;
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref1, 0.0, DoubleType.cType);
        /*AtsInsMove*/tmp8 = /*FuncCallNode*/ATSLIB_056$prelude_string_foreach_env$3$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, /*AtsPmvPtrof*/RefType.ptrof(tmpref1));
        /*AtsInsMove*/tmp39 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_add_guint$14$1(/*AtsPmvCastFn*//*castfn_currently_no_op*//*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, tmp8);
        /*AtsInsMove*/tmp50 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats_atof_frac(/*AtsPmvCastFn*//*castfn_currently_no_op*/tmp39);
        /*AtsInsMove*/tmpret0 = /*FuncCallNode*/CCompFloat.atspre_g0float_add_double((Double)RefType.cloneValue(tmpref1, DoubleType.cType), tmp50);
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Double loop_21(Ptrk arg0, Double arg1, Double arg2) {
        /*BlockNode*//*DefinitionNode*/Double tmpret52 = /*DefinitionNode*/null;
        /*DefinitionNode*/Character tmp53 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp56 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp57 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp59 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp60 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp61 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp62 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp63 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp53 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$7$2(arg0);
        /*AtsInsMove*/tmp56 = /*FuncCallNode*/CCompChar.atspre_isdigit_char(tmp53);
        /*IfNode*/if (tmp56 == true) {
            /*BlockNode*//*AtsInsMove*/tmp57 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_succ$12$2(arg0);
            /*AtsInsMove*/tmp60 = /*FuncCallNode*/CCompFloat.atspre_mul_int_double(10, arg1);
            /*AtsInsMove*/tmp62 = /*FuncCallNode*/CCompChar.atspre_sub_char0_char0(tmp53, '0');
            /*AtsInsMove*/tmp61 = /*FuncCallNode*/CCompFloat.atspre_g0int2float_int_double(tmp62);
            /*AtsInsMove*/tmp59 = /*FuncCallNode*/CCompFloat.atspre_g0float_add_double(tmp60, tmp61);
            /*AtsInsMove*/tmp63 = /*FuncCallNode*/CCompFloat.atspre_mul_int_double(10, arg2);
            /*AtsInsMove*/tmpret52 = /*FuncCallNode*/loop_21(tmp57, tmp59, tmp63);
        } else {
            /*BlockNode*//*AtsInsMove*/tmpret52 = /*FuncCallNode*/CCompFloat.atspre_g0float_div_double(arg1, arg2);
        }
        /*AtsReturn*/return tmpret52;
    }

    /*UserFunc*/static public Character ATSLIB_056$prelude_056$unsafe_ptr0_get$7$2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Character tmp27$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Character tmp28$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp28$2 = (Character)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, CharType.cType);
        /*AtsInsMove*/tmp27$2 = tmp28$2;
        /*AtsReturn*/return tmp27$2;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_succ$12$2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp37$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp37$2 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, /*AtsPmvSizeofNode*/1);
        /*AtsReturn*/return tmp37$2;
    }

    /*UserFunc*/static public Character ATSLIB_056$prelude_056$unsafe_ptr0_get$7$3(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Character tmp27$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Character tmp28$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp28$3 = (Character)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, CharType.cType);
        /*AtsInsMove*/tmp27$3 = tmp28$3;
        /*AtsReturn*/return tmp27$3;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_succ$26$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp69$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp70$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp70$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_succ$12$3(arg0);
        /*AtsInsMove*/tmp69$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp70$1;
        /*AtsReturn*/return tmp69$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_succ$12$3(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp37$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp37$3 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, /*AtsPmvSizeofNode*/1);
        /*AtsReturn*/return tmp37$3;
    }

    /*UserFunc*/static public Double _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats_atof_frac(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Double tmpret51 = /*DefinitionNode*/null;
        /*DefinitionNode*/Character tmp64 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp67 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp68 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp64 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$7$3(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0);
        /*AtsInsMove*/tmp67 = /*FuncCallNode*/CCompChar.atspre_eq_char0_char0(tmp64, '.');
        /*IfNode*/if (tmp67 == true) {
            /*BlockNode*//*AtsInsMove*/tmp68 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_succ$26$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0);
            /*AtsInsMove*/tmpret51 = /*FuncCallNode*/loop_21(tmp68, 0.0, 1.0);
        } else {
            /*BlockNode*//*AtsInsMove*/tmpret51 = 0.0;
        }
        /*AtsReturn*/return tmpret51;
    }

    /*UserFunc*/static public SingletonValue atof_usage_29(Ptrk arg0) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_prerr_string(StringType.fromString("Usage: "));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_prerr_string(arg0);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_prerr_string(StringType.fromString(" [float]"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_prerr_newline();
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gte_g1int_int$31$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp81$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp82$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp82$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp81$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_gte_int(arg0, tmp82$1);
        /*AtsReturn*/return tmp81$1;
    }

    /*UserFunc*/static public SingletonValue mainats_argc_argv_0(Integer arg0, Ptrk arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp79 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp80 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp89 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp80 = /*FuncCallNode*/ATSLIB_056$prelude_gte_g1int_int$31$1(arg0, 2);
        /*IfNode*/if (tmp80 == true) {
            /*BlockNode*//*AtsInsMove*/tmp79 = /*FuncCallNode*/CCompBasics.atspre_argv_get_at(arg1, 1);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp79 = StringType.fromString("3.1416");
        }
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("atof(\""));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(tmp79);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("\") = "));
        /*AtsInsMove*/tmp89 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats_atof(tmp79);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompFloat.atspre_print_double(tmp89);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$atof_056$dats__dynload();
        Integer mainArgc = args.length + 1;

        Ptrk[] arrArgv = new Ptrk[mainArgc];
        arrArgv[0] = StringType.fromString("atof_dats");

        for (int i = 1; i < mainArgc; ++i) {
            arrArgv[i] = StringType.fromString(args[i - 1]);
        }

        ArrayType argvTy = new ArrayType(PtrkType.cType, mainArgc);

        Ptrk mainArgv = Ptrk.createPtrk(argvTy, arrArgv);
        mainats_argc_argv_0(mainArgc, mainArgv);
    }


}


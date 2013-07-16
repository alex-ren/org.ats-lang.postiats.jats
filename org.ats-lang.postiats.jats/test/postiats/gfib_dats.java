
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class gfib_dats {
    /*UserFunc*/static public Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp0$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp1$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp2$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp3$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp4$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp5$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp6$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp8$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp2$1 = /*FuncCallNode*/ATSLIB_056$prelude_gnumber_int$3$1(2);
        /*AtsInsMove*/tmp1$1 = /*FuncCallNode*/ATSLIB_056$prelude_ggte_val$5$1(arg0, tmp2$1);
        /*IfNode*/if (tmp1$1 == true) {
            /*BlockNode*//*AtsInsMove*/tmp5$1 = /*FuncCallNode*/ATSLIB_056$prelude_gnumber_int$3$2(1);
            /*AtsInsMove*/tmp4$1 = /*FuncCallNode*/ATSLIB_056$prelude_gsub_val$13$1(arg0, tmp5$1);
            /*AtsInsMove*/tmp3$1 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(tmp4$1);
            /*AtsInsMove*/tmp8$1 = /*FuncCallNode*/ATSLIB_056$prelude_gnumber_int$3$3(2);
            /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/ATSLIB_056$prelude_gsub_val$13$2(arg0, tmp8$1);
            /*AtsInsMove*/tmp6$1 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(tmp7$1);
            /*AtsInsMove*/tmp0$1 = /*FuncCallNode*/ATSLIB_056$prelude_gadd_val$18$1(tmp3$1, tmp6$1);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp0$1 = arg0;
        }
        /*AtsReturn*/return tmp0$1;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_gnumber_int$3$1(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp22$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp22$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg0);
        /*AtsReturn*/return tmp22$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_ggte_val$5$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp24$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp25$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp25$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_compare_int(arg0, arg1);
        /*AtsInsMove*/tmp24$1 = /*FuncCallNode*/ATSLIB_056$prelude_gte_g0int_int$8$1(tmp25$1, 0);
        /*AtsReturn*/return tmp24$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gte_g0int_int$8$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp28$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp29$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp29$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp28$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_gte_int(arg0, tmp29$1);
        /*AtsReturn*/return tmp28$1;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_gnumber_int$3$2(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp22$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp22$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg0);
        /*AtsReturn*/return tmp22$2;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_gsub_val$13$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp33$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp33$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_sub_int(arg0, arg1);
        /*AtsReturn*/return tmp33$1;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_gnumber_int$3$3(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp22$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp22$3 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg0);
        /*AtsReturn*/return tmp22$3;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_gsub_val$13$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp33$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp33$2 = /*FuncCallNode*/CCompInteger.atspre_g0int_sub_int(arg0, arg1);
        /*AtsReturn*/return tmp33$2;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_gadd_val$18$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp37$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp37$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_add_int(arg0, arg1);
        /*AtsReturn*/return tmp37$1;
    }

    /*UserFunc*/static public Double _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(Double arg0) {
        /*BlockNode*//*DefinitionNode*/Double tmp0$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp1$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp2$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp3$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp4$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp5$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp6$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp7$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp8$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp2$2 = /*FuncCallNode*/ATSLIB_056$prelude_gnumber_int$22$1(2);
        /*AtsInsMove*/tmp1$2 = /*FuncCallNode*/ATSLIB_056$prelude_ggte_val$5$2(arg0, tmp2$2);
        /*IfNode*/if (tmp1$2 == true) {
            /*BlockNode*//*AtsInsMove*/tmp5$2 = /*FuncCallNode*/ATSLIB_056$prelude_gnumber_int$22$2(1);
            /*AtsInsMove*/tmp4$2 = /*FuncCallNode*/ATSLIB_056$prelude_gsub_val$28$1(arg0, tmp5$2);
            /*AtsInsMove*/tmp3$2 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(tmp4$2);
            /*AtsInsMove*/tmp8$2 = /*FuncCallNode*/ATSLIB_056$prelude_gnumber_int$22$3(2);
            /*AtsInsMove*/tmp7$2 = /*FuncCallNode*/ATSLIB_056$prelude_gsub_val$28$2(arg0, tmp8$2);
            /*AtsInsMove*/tmp6$2 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(tmp7$2);
            /*AtsInsMove*/tmp0$2 = /*FuncCallNode*/ATSLIB_056$prelude_gadd_val$33$1(tmp3$2, tmp6$2);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp0$2 = arg0;
        }
        /*AtsReturn*/return tmp0$2;
    }

    /*UserFunc*/static public Double ATSLIB_056$prelude_gnumber_int$22$1(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Double tmp52$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp52$1 = /*FuncCallNode*/CCompFloat.atspre_g0int2float_int_double(arg0);
        /*AtsReturn*/return tmp52$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_ggte_val$5$2(Double arg0, Double arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp24$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp25$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp25$2 = /*FuncCallNode*/CCompFloat.atspre_g0float_compare_double(arg0, arg1);
        /*AtsInsMove*/tmp24$2 = /*FuncCallNode*/ATSLIB_056$prelude_gte_g0int_int$8$2(tmp25$2, 0);
        /*AtsReturn*/return tmp24$2;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gte_g0int_int$8$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp28$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp29$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp29$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp28$2 = /*FuncCallNode*/CCompInteger.atspre_g0int_gte_int(arg0, tmp29$2);
        /*AtsReturn*/return tmp28$2;
    }

    /*UserFunc*/static public Double ATSLIB_056$prelude_gnumber_int$22$2(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Double tmp52$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp52$2 = /*FuncCallNode*/CCompFloat.atspre_g0int2float_int_double(arg0);
        /*AtsReturn*/return tmp52$2;
    }

    /*UserFunc*/static public Double ATSLIB_056$prelude_gsub_val$28$1(Double arg0, Double arg1) {
        /*BlockNode*//*DefinitionNode*/Double tmp59$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp59$1 = /*FuncCallNode*/CCompFloat.atspre_g0float_sub_double(arg0, arg1);
        /*AtsReturn*/return tmp59$1;
    }

    /*UserFunc*/static public Double ATSLIB_056$prelude_gnumber_int$22$3(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Double tmp52$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp52$3 = /*FuncCallNode*/CCompFloat.atspre_g0int2float_int_double(arg0);
        /*AtsReturn*/return tmp52$3;
    }

    /*UserFunc*/static public Double ATSLIB_056$prelude_gsub_val$28$2(Double arg0, Double arg1) {
        /*BlockNode*//*DefinitionNode*/Double tmp59$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp59$2 = /*FuncCallNode*/CCompFloat.atspre_g0float_sub_double(arg0, arg1);
        /*AtsReturn*/return tmp59$2;
    }

    /*UserFunc*/static public Double ATSLIB_056$prelude_gadd_val$33$1(Double arg0, Double arg1) {
        /*BlockNode*//*DefinitionNode*/Double tmp63$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp63$1 = /*FuncCallNode*/CCompFloat.atspre_g0float_add_double(arg0, arg1);
        /*AtsReturn*/return tmp63$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Integer tmp12 = /*DefinitionNode*/null;
        /*DefinitionNode*/Double tmp42 = /*DefinitionNode*/null;
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("fib(20) = "));
        /*AtsInsMove*/tmp12 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(20);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompInteger.atspre_print_int(tmp12);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("fib(20) = "));
        /*AtsInsMove*/tmp42 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(20.0);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompFloat.atspre_print_double(tmp42);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynload();
        mainats_void_0();
    }


}


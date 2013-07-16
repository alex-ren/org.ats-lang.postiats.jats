
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class fact_dats {
    /*UserFunc*/static public Integer fact_0(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp6 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp8 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp1 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g1int_int$1$1(arg0, 0);
        /*IfNode*/if (tmp1 == true) {
            /*BlockNode*//*AtsInsMove*/tmp7 = /*FuncCallNode*/CCompInteger.atspre_g1int_sub_int(arg0, 1);
            /*AtsInsMove*/tmp6 = /*FuncCallNode*/fact_0(tmp7);
            /*AtsInsMove*/tmp8 = /*FuncCallNode*/CCompInteger.atspre_g1int_mul_int(arg0, tmp6);
            /*AtsInsMove*/tmpret0 = tmp8;
        } else {
            /*BlockNode*//*AtsInsMove*/tmpret0 = 1;
        }
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g1int_int$1$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp2$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp3$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp3$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp2$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_gt_int(arg0, tmp3$1);
        /*AtsReturn*/return tmp2$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$8$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp19$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp20$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp20$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp19$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp20$1);
        /*AtsReturn*/return tmp19$1;
    }

    /*UserFunc*/static public Integer mainats_void_int() {
        /*BlockNode*//*DefinitionNode*/Integer tmpret9 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp10 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp11 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp18 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp10 = /*FuncCallNode*/fact_0(12);
        /*AtsInsMove*/tmp11 = /*ats_sel_recsin_st*/tmp10;
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("fact("));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompInteger.atspre_print_int(12);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString(") = "));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompInteger.atspre_print_int(tmp11);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMove*/tmp18 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$8$1(tmp11, 479001600);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp18, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/fact.dats: 817(line=45, offs=12) -- 843(line=45, offs=38)"));
        /*AtsInsMove*/tmpret9 = 0;
        /*AtsReturn*/return tmpret9;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fact_056$dats__dynload();
        mainats_void_int();
    }


}


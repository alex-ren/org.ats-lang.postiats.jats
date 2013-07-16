
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class f91_dats {
    /*UserFunc*/static public Integer f91_0(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp6 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp1 = /*FuncCallNode*/ATSLIB_056$prelude_gte_g0int_int$1$1(arg0, 101);
        /*IfNode*/if (tmp1 == true) {
            /*BlockNode*//*AtsInsMove*/tmpret0 = /*FuncCallNode*/CCompInteger.atspre_g0int_sub_int(arg0, 10);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp7 = /*FuncCallNode*/CCompInteger.atspre_g0int_add_int(arg0, 11);
            /*AtsInsMove*/tmp6 = /*FuncCallNode*/f91_0(tmp7);
            /*AtsInsMove*/tmpret0 = /*FuncCallNode*/f91_0(tmp6);
        }
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gte_g0int_int$1$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp2$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp3$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp3$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp2$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_gte_int(arg0, tmp3$1);
        /*AtsReturn*/return tmp2$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$1);
        /*AtsReturn*/return tmp11$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$2 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$2);
        /*AtsReturn*/return tmp11$2;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$3(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$3 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$3 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$3);
        /*AtsReturn*/return tmp11$3;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$4(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$4 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$4 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$4 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$4);
        /*AtsReturn*/return tmp11$4;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$5(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$5 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$5 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$5 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$5);
        /*AtsReturn*/return tmp11$5;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$6(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$6 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$6 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$6 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$6 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$6);
        /*AtsReturn*/return tmp11$6;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$7(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$7 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$7 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$7 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$7 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$7);
        /*AtsReturn*/return tmp11$7;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$8(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$8 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$8 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$8 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$8 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$8);
        /*AtsReturn*/return tmp11$8;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$9(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$9 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$9 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$9 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$9 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$9);
        /*AtsReturn*/return tmp11$9;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$8$10(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$10 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$10 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$10 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$10 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$10);
        /*AtsReturn*/return tmp11$10;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Boolean tmp10 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp15 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp16 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp18 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp21 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp22 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp24 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp27 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp28 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp30 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp33 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp34 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp36 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp39 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp40 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp42 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp45 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp46 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp48 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp51 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp52 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp54 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp57 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp58 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp60 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp63 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp64 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp66 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp69 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp70 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp16 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 0);
        /*AtsInsMove*/tmp15 = /*FuncCallNode*/f91_0(tmp16);
        /*AtsInsMove*/tmp10 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$1(tmp15, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp10, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 326(line=22, offs=10) -- 351(line=22, offs=35)"));
        /*AtsInsMove*/tmp22 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 1);
        /*AtsInsMove*/tmp21 = /*FuncCallNode*/f91_0(tmp22);
        /*AtsInsMove*/tmp18 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$2(tmp21, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp18, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 362(line=23, offs=10) -- 387(line=23, offs=35)"));
        /*AtsInsMove*/tmp28 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 2);
        /*AtsInsMove*/tmp27 = /*FuncCallNode*/f91_0(tmp28);
        /*AtsInsMove*/tmp24 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$3(tmp27, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp24, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 398(line=24, offs=10) -- 423(line=24, offs=35)"));
        /*AtsInsMove*/tmp34 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 3);
        /*AtsInsMove*/tmp33 = /*FuncCallNode*/f91_0(tmp34);
        /*AtsInsMove*/tmp30 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$4(tmp33, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp30, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 434(line=25, offs=10) -- 459(line=25, offs=35)"));
        /*AtsInsMove*/tmp40 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 4);
        /*AtsInsMove*/tmp39 = /*FuncCallNode*/f91_0(tmp40);
        /*AtsInsMove*/tmp36 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$5(tmp39, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp36, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 470(line=26, offs=10) -- 495(line=26, offs=35)"));
        /*AtsInsMove*/tmp46 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 5);
        /*AtsInsMove*/tmp45 = /*FuncCallNode*/f91_0(tmp46);
        /*AtsInsMove*/tmp42 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$6(tmp45, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp42, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 506(line=27, offs=10) -- 531(line=27, offs=35)"));
        /*AtsInsMove*/tmp52 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 6);
        /*AtsInsMove*/tmp51 = /*FuncCallNode*/f91_0(tmp52);
        /*AtsInsMove*/tmp48 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$7(tmp51, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp48, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 542(line=28, offs=10) -- 567(line=28, offs=35)"));
        /*AtsInsMove*/tmp58 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 7);
        /*AtsInsMove*/tmp57 = /*FuncCallNode*/f91_0(tmp58);
        /*AtsInsMove*/tmp54 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$8(tmp57, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp54, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 578(line=29, offs=10) -- 603(line=29, offs=35)"));
        /*AtsInsMove*/tmp64 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 8);
        /*AtsInsMove*/tmp63 = /*FuncCallNode*/f91_0(tmp64);
        /*AtsInsMove*/tmp60 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$9(tmp63, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp60, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 614(line=30, offs=10) -- 639(line=30, offs=35)"));
        /*AtsInsMove*/tmp70 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(0, 9);
        /*AtsInsMove*/tmp69 = /*FuncCallNode*/f91_0(tmp70);
        /*AtsInsMove*/tmp66 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$8$10(tmp69, 91);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp66, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/f91.dats: 650(line=31, offs=10) -- 675(line=31, offs=35)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$f91_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$f91_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$f91_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$f91_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$f91_056$dats__dynload();
        mainats_void_0();
    }


}



package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class fib_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", IntType.cType);
    }

    /*UserFunc*/static public SingletonValue loop_1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp8 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp9 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp10 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp2 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g0int_int$2$1(arg1, 0);
        /*IfNode*/if (tmp2 == true) {
            /*BlockNode*//*AtsInsMove*/tmp7 = (Integer)RefType.cloneValue(RefType.SelFltrecOfs(arg0, "atslab$0", postiats_tyrec_0), IntType.cType);
            /*AtsInsMove*/tmp8 = (Integer)RefType.cloneValue(RefType.SelFltrecOfs(arg0, "atslab$1", postiats_tyrec_0), IntType.cType);
            /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(arg0, "atslab$0", postiats_tyrec_0), tmp8, IntType.cType);
            /*AtsInsMove*/tmp9 = /*FuncCallNode*/CCompInteger.atspre_g0int_add_int(tmp7, tmp8);
            /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(arg0, "atslab$1", postiats_tyrec_0), tmp9, IntType.cType);
            /*AtsInsMove*/tmp10 = /*FuncCallNode*/CCompInteger.atspre_g0int_sub_int(arg1, 1);
            /*AtsInsMoveVoid*//*FuncCallNode*/loop_1(arg0, tmp10);
        } else {
            /*BlockNode*//*AtsInsMoveVoid*/
        }
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g0int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp3$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp4$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp4$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp3$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_gt_int(arg0, tmp4$1);
        /*AtsReturn*/return tmp3$1;
    }

    /*UserFunc*/static public Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fib_056$dats_fib(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref11 = /*DefinitionNode*/postiats_tyrec_0.createRefDefault();
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref11, 0, "atslab$0", postiats_tyrec_0);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref11, 1, "atslab$1", postiats_tyrec_0);
        /*AtsInsMoveVoid*//*FuncCallNode*/loop_1(/*AtsPmvPtrof*/RefType.ptrof(tmpref11), arg0);
        /*AtsInsMove*/tmpret0 = (Integer)RefType.cloneValue(RefType.SelFltrecOfs(tmpref11, "atslab$0", postiats_tyrec_0), IntType.cType);
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$9$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp20$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp21$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp21$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp20$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp21$1);
        /*AtsReturn*/return tmp20$1;
    }

    /*UserFunc*/static public Integer mainats_void_int() {
        /*BlockNode*//*DefinitionNode*/Integer tmpret13 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp14 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp19 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp14 = /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fib_056$dats_fib(3);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("ans = "));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompInteger.atspre_print_int(tmp14);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMove*/tmp19 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$9$1(tmp14, 2);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp19, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/fib.dats: 853(line=45, offs=12) -- 871(line=45, offs=30)"));
        /*AtsInsMove*/tmpret13 = 0;
        /*AtsReturn*/return tmpret13;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fib_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fib_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fib_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fib_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$fib_056$dats__dynload();
        mainats_void_int();
    }


}



package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test17_dats {
    /*UserFunc*/static public Integer foo2_0(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = arg0;
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_gt_g0int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp4$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp5$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp5$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp4$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_gt_int(arg0, tmp5$1);
        /*AtsReturn*/return tmp4$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Integer tmp2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp2 = /*FuncCallNode*/foo2_0(3);
        /*AtsInsMove*/tmp3 = /*FuncCallNode*/ATSLIB_056$prelude_gt_g0int_int$2$1(tmp2, 3);
        /*IfNode*/if (tmp3 == true) {
            /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(false, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test17.dats: 135(line=13, offs=17) -- 151(line=13, offs=33)"));
        } else {
            /*BlockNode*//*AtsInsMoveVoid*/
        }
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test17_056$dats__dynload();
        mainats_void_0();
    }


}


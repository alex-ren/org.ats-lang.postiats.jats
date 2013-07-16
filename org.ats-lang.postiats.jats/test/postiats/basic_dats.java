
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class basic_dats {
    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp4$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp5$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp5$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp4$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp5$1);
        /*AtsReturn*/return tmp4$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Integer tmp1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp1 = /*FuncCallNode*/CCompInteger.atspre_g1int_add_int(1, 1);
        /*AtsInsMove*/tmp3 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$2$1(tmp1, 2);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp3, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/basic.dats: 243(line=20, offs=10) -- 259(line=20, offs=26)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$basic_056$dats__dynload();
        mainats_void_0();
    }


}



package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test18_dats {
    /*UserFunc*/static public Integer foo2_0(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsStore*/RefType.updateFromNonRefType(arg0, 3, IntType.cType);
        /*AtsInsMove*/tmpret0 = 3;
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp7$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp8$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp8$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp8$1);
        /*AtsReturn*/return tmp7$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$2$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp7$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp8$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp8$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp7$2 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp8$2);
        /*AtsReturn*/return tmp7$2;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref2 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp6 = /*DefinitionNode*/null;
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref2, 3, IntType.cType);
        /*AtsInsMove*/tmp3 = /*FuncCallNode*/foo2_0(/*AtsPmvPtrof*/RefType.ptrof(tmpref2));
        /*AtsInsMove*/tmp6 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$2$1((Integer)RefType.cloneValue(tmpref2, IntType.cType), tmp3);
        /*IfNode*/if (tmp6 == true) {
            /*BlockNode*//*AtsInsMove*/tmp5 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$2$2(tmp3, 3);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp5 = false;
        }
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp5, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test18.dats: 174(line=18, offs=12) -- 199(line=18, offs=37)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test18_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test18_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test18_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test18_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test18_056$dats__dynload();
        mainats_void_0();
    }


}


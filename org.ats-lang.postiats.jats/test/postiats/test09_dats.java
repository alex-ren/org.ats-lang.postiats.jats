
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test09_dats {
    /*UserFunc*/static public Integer foo_int_0(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = arg0;
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp7$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp8$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp8$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp8$1);
        /*AtsReturn*/return tmp7$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref2 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Ptrk tmpref3 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp6 = /*DefinitionNode*/null;
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref3, 2, IntType.cType);
        /*AtsInsMove*/tmp4 = /*FuncCallNode*/foo_int_0(3);
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref2, tmp4, IntType.cType);
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref3, tmp4, IntType.cType);
        /*AtsInsMove*/tmp6 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$2$1((Integer)RefType.cloneValue(tmpref2, IntType.cType), (Integer)RefType.cloneValue(tmpref3, IntType.cType));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp6, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test09.dats: 196(line=12, offs=12) -- 214(line=12, offs=30)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test09_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test09_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test09_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test09_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test09_056$dats__dynload();
        mainats_void_0();
    }


}


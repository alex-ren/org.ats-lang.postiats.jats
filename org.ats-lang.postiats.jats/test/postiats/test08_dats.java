
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test08_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", IntType.cType);
    }

    /*UserFunc*/static public Integer foo_0(Map<String, Object> arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = /*AtsSelFltRec*/(Integer)arg0.get("atslab$0");
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp6$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp7$1);
        /*AtsReturn*/return tmp6$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref2 = /*DefinitionNode*/postiats_tyrec_0.createRefDefault();
        /*DefinitionNode*/Integer tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp5 = /*DefinitionNode*/null;
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref2, 1, "atslab$0", postiats_tyrec_0);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref2, 2, "atslab$1", postiats_tyrec_0);
        /*AtsInsMove*/tmp3 = /*FuncCallNode*/foo_0((Map<String, Object>)RefType.cloneValue(tmpref2, postiats_tyrec_0));
        /*AtsInsMove*/tmp5 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$2$1(tmp3, 1);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp5, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test08.dats: 147(line=10, offs=12) -- 163(line=10, offs=28)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test08_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test08_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test08_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test08_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test08_056$dats__dynload();
        mainats_void_0();
    }


}


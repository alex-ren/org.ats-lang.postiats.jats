
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test10_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", IntType.cType);
    }

    /*UserFunc*/static public Integer foo_0(Map<String, Object> arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = /*AtsSelBoxRec*/(Integer)arg0.get("atslab$0");
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp8$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp9$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp9$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp8$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp9$1);
        /*AtsReturn*/return tmp8$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref2 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp7 = /*DefinitionNode*/null;
        /*AtsInsMoveBoxrec*/RefType.updateFromNonRefType(tmpref2, new HashMap<String, Object>(), BoxedType.cType);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref2.getValue(BoxedType.cType)).put("atslab$0", 1);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref2.getValue(BoxedType.cType)).put("atslab$1", 2);
        /*AtsInsMove*/tmp3 = /*FuncCallNode*/foo_0((Map<String, Object>)RefType.cloneValue(tmpref2, postiats_tyrec_0));
        /*AtsInsMoveBoxrec*/tmp4 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp4.put("atslab$0", 3);
        /*AtsInsStoreBoxrecOfs*/tmp4.put("atslab$1", 4);
        /*AtsInsMove*/tmp5 = /*FuncCallNode*/foo_0(tmp4);
        /*AtsInsMove*/tmp7 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$2$1(tmp5, 3);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp7, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test10.dats: 185(line=13, offs=12) -- 202(line=13, offs=29)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test10_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test10_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test10_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test10_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test10_056$dats__dynload();
        mainats_void_0();
    }


}



package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test16_dats {
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

    /*UserFunc*/static public Integer foo2_1(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret1 = arg0;
        /*AtsReturn*/return tmpret1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$3$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$1);
        /*AtsReturn*/return tmp11$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$3$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$2 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$2);
        /*AtsReturn*/return tmp11$2;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Map<String, Object> tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref4 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp6 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp9 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp10 = /*DefinitionNode*/null;
        /*AtsInsMoveBoxrec*/tmp3 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp3.put("atslab$0", 1);
        /*AtsInsStoreBoxrecOfs*/tmp3.put("atslab$1", 2);
        /*AtsInsMoveBoxrec*/RefType.updateFromNonRefType(tmpref4, new HashMap<String, Object>(), BoxedType.cType);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref4.getValue(BoxedType.cType)).put("atslab$0", 3);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref4.getValue(BoxedType.cType)).put("atslab$1", 4);
        /*AtsInsMove*/tmp5 = /*FuncCallNode*/foo_0(tmp3);
        /*AtsInsMove*/tmp6 = /*FuncCallNode*/foo2_1(/*AtsSelBoxRec*/(Integer)tmp3.get("atslab$0"));
        /*AtsInsMove*/tmp7 = /*FuncCallNode*/foo2_1(/*AtsSelBoxRec*/(Integer)((Map<String, Object>)RefType.getValue(tmpref4, BoxedType.cType)).get("atslab$0"));
        /*AtsInsMove*/tmp10 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$3$1(tmp5, 1);
        /*IfNode*/if (tmp10 == true) {
            /*BlockNode*//*AtsInsMove*/tmp9 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$3$2(tmp7, 3);
        } else {
            /*BlockNode*//*AtsInsMove*/tmp9 = false;
        }
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp9, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test16.dats: 239(line=16, offs=12) -- 265(line=16, offs=38)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test16_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test16_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test16_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test16_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test16_056$dats__dynload();
        mainats_void_0();
    }


}


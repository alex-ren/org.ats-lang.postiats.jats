
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test11_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", BoxedType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_1 = new StructType("postiats_tyrec_1");
    static {
        /*StructType*/postiats_tyrec_1.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_1.addMember("atslab$1", IntType.cType);
    }

    /*UserFunc*/static public Integer foo_0(Map<String, Object> arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = /*AtsSelBoxRec*/(Integer)arg0.get("atslab$0");
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp10$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp11$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp11$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp10$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp11$1);
        /*AtsReturn*/return tmp10$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Map<String, Object> tmp2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref3 = /*DefinitionNode*/BoxedType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp6 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp9 = /*DefinitionNode*/null;
        /*AtsInsMoveBoxrec*/tmp2 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp2.put("atslab$0", 4);
        /*AtsInsStoreBoxrecOfs*/tmp2.put("atslab$1", 6);
        /*AtsInsMoveBoxrec*/RefType.updateFromNonRefType(tmpref3, new HashMap<String, Object>(), BoxedType.cType);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref3.getValue(BoxedType.cType)).put("atslab$0", 1);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref3.getValue(BoxedType.cType)).put("atslab$1", tmp2);
        /*AtsInsMove*/tmp4 = /*FuncCallNode*/foo_0((Map<String, Object>)RefType.cloneValue(tmpref3, postiats_tyrec_0));
        /*AtsInsMoveBoxrec*/tmp6 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp6.put("atslab$0", 2);
        /*AtsInsStoreBoxrecOfs*/tmp6.put("atslab$1", 3);
        /*AtsInsMoveBoxrec*/tmp5 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp5.put("atslab$0", 1);
        /*AtsInsStoreBoxrecOfs*/tmp5.put("atslab$1", tmp6);
        /*AtsInsMove*/tmp7 = /*FuncCallNode*/foo_0(tmp5);
        /*AtsInsMove*/tmp9 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$2$1(tmp7, 1);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp9, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test11.dats: 252(line=17, offs=12) -- 269(line=17, offs=29)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test11_056$dats__dynload();
        mainats_void_0();
    }


}


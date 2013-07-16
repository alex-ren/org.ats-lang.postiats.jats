
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test02_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", PtrkType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_1 = new StructType("postiats_tyrec_1");
    static {
        /*StructType*/postiats_tyrec_1.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_1.addMember("atslab$1", IntType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_2 = new StructType("postiats_tyrec_2");
    static {
        /*StructType*/postiats_tyrec_2.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_2.addMember("atslab$1", BoxedType.cType);
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$1$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp9$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp10$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp10$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp9$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp10$1);
        /*AtsReturn*/return tmp9$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref1 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref3 = /*DefinitionNode*/postiats_tyrec_0.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref5 = /*DefinitionNode*/postiats_tyrec_0.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp6 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp8 = /*DefinitionNode*/null;
        /*AtsInsMoveBoxrec*/RefType.updateFromNonRefType(tmpref1, new HashMap<String, Object>(), BoxedType.cType);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref1.getValue(BoxedType.cType)).put("atslab$0", 1);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref1.getValue(BoxedType.cType)).put("atslab$1", 2);
        /*AtsInsMoveBoxrec*/tmp2 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp2.put("atslab$0", 2);
        /*AtsInsStoreBoxrecOfs*/tmp2.put("atslab$1", 3);
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref1, tmp2, postiats_tyrec_1);
        /*AtsInsMoveBoxrec*/tmp4 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp4.put("atslab$0", 2);
        /*AtsInsStoreBoxrecOfs*/tmp4.put("atslab$1", 3);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref3, 1, "atslab$0", postiats_tyrec_2);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref3, tmp4, "atslab$1", postiats_tyrec_2);
        /*AtsInsMoveBoxrec*/tmp6 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp6.put("atslab$0", 21);
        /*AtsInsStoreBoxrecOfs*/tmp6.put("atslab$1", 31);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref5, 11, "atslab$0", postiats_tyrec_2);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref5, tmp6, "atslab$1", postiats_tyrec_2);
        /*AtsInsStore*/RefType.updateFromRefType(tmpref3, tmpref5, postiats_tyrec_0);
        /*AtsInsMove*/tmp8 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$1$1(/*AtsSelBoxRec*/(Integer)((Map<String, Object>)RefType.getValue(RefType.SelFltrecOfs(tmpref5, "atslab$1", postiats_tyrec_2), BoxedType.cType)).get("atslab$0"), 21);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp8, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test02.dats: 247(line=13, offs=12) -- 269(line=13, offs=34)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test02_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test02_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test02_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test02_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test02_056$dats__dynload();
        mainats_void_0();
    }


}


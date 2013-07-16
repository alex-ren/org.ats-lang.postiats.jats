
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test13_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", IntType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_1 = new StructType("postiats_tyrec_1");
    static {
        /*StructType*/postiats_tyrec_1.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_1.addMember("atslab$1", postiats_tyrec_0);
    }

    /*UserFunc*/static public Integer foo_int_0(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = arg0;
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$2$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp15$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp16$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp16$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp15$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp16$1);
        /*AtsReturn*/return tmp15$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref2 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref4 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp5 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Ptrk tmpref6 = /*DefinitionNode*/postiats_tyrec_0.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp7 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Integer tmp8 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp9 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Ptrk tmpref10 = /*DefinitionNode*/postiats_tyrec_0.createRefDefault();
        /*DefinitionNode*/Ptrk tmpref11 = /*DefinitionNode*/postiats_tyrec_1.createRefDefault();
        /*DefinitionNode*/Integer tmp12 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp14 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp3 = /*FuncCallNode*/foo_int_0(3);
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref2, tmp3, IntType.cType);
        /*AtsInsStore*/RefType.updateFromRefType(tmpref4, tmpref2, IntType.cType);
        /*AtsInsStoreFltrecOfs*/tmp5.put("atslab$0", 1);
        /*AtsInsStoreFltrecOfs*/tmp5.put("atslab$1", 2);
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref6, tmp5, postiats_tyrec_0);
        /*AtsInsMove*/tmp7 = (Map<String, Object>)RefType.cloneValue(tmpref6, postiats_tyrec_0);
        /*AtsInsMove*/tmp8 = /*FuncCallNode*/foo_int_0(/*AtsSelFltRec*/(Integer)tmp7.get("atslab$0"));
        /*AtsInsStoreFltrecOfs*/tmp9.put("atslab$0", 3);
        /*AtsInsStoreFltrecOfs*/tmp9.put("atslab$1", 4);
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref6, tmp9, postiats_tyrec_0);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(tmpref6, "atslab$0", postiats_tyrec_0), 6, IntType.cType);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(tmpref10, "atslab$0", postiats_tyrec_0), 3, IntType.cType);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref11, "atslab$1", postiats_tyrec_1), "atslab$0", postiats_tyrec_0), 3, IntType.cType);
        /*AtsInsMove*/tmp12 = /*FuncCallNode*/foo_int_0((Integer)RefType.cloneValue(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref11, "atslab$1", postiats_tyrec_1), "atslab$0", postiats_tyrec_0), IntType.cType));
        /*AtsInsMove*/tmp14 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$2$1((Integer)RefType.cloneValue(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref11, "atslab$1", postiats_tyrec_1), "atslab$0", postiats_tyrec_0), IntType.cType), 3);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp14, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test13.dats: 450(line=28, offs=12) -- 471(line=28, offs=33)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test13_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test13_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test13_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test13_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test13_056$dats__dynload();
        mainats_void_0();
    }


}


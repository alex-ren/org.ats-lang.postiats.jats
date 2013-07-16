
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test20_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$x1", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$x2", IntType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_1 = new StructType("postiats_tyrec_1");
    static {
        /*StructType*/postiats_tyrec_1.addMember("atslab$x1", IntType.cType);
        /*StructType*/postiats_tyrec_1.addMember("atslab$x2", postiats_tyrec_0);
    }

    /*UserFunc*/static public SingletonValue foo_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref1 = /*DefinitionNode*/postiats_tyrec_1.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp2 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Map<String, Object> tmp3 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Boolean tmp5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp11 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp15 = /*DefinitionNode*/null;
        /*AtsInsStoreFltrecOfs*/tmp2.put("atslab$x1", 2);
        /*AtsInsStoreFltrecOfs*/tmp2.put("atslab$x2", 3);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref1, 1, "atslab$x1", postiats_tyrec_1);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref1, tmp2, "atslab$x2", postiats_tyrec_1);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(tmpref1, "atslab$x1", postiats_tyrec_1), 11, IntType.cType);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref1, "atslab$x2", postiats_tyrec_1), "atslab$x1", postiats_tyrec_0), 22, IntType.cType);
        /*AtsInsStoreFltrecOfs*/tmp3.put("atslab$x1", 22);
        /*AtsInsStoreFltrecOfs*/tmp3.put("atslab$x2", 33);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(tmpref1, "atslab$x2", postiats_tyrec_1), tmp3, postiats_tyrec_0);
        /*AtsInsMove*/tmp5 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$1$1((Integer)RefType.cloneValue(RefType.SelFltrecOfs(tmpref1, "atslab$x1", postiats_tyrec_1), IntType.cType), 11);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp5, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 280(line=16, offs=10) -- 301(line=16, offs=31)"));
        /*AtsInsMove*/tmp11 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$1$2((Integer)RefType.cloneValue(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref1, "atslab$x2", postiats_tyrec_1), "atslab$x1", postiats_tyrec_0), IntType.cType), 22);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp11, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 312(line=17, offs=10) -- 336(line=17, offs=34)"));
        /*AtsInsMove*/tmp15 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$1$3((Integer)RefType.cloneValue(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref1, "atslab$x2", postiats_tyrec_1), "atslab$x2", postiats_tyrec_0), IntType.cType), 33);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp15, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 347(line=18, offs=10) -- 371(line=18, offs=34)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$1$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp6$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp7$1);
        /*AtsReturn*/return tmp6$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$1$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp7$2 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp6$2 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp7$2);
        /*AtsReturn*/return tmp6$2;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$1$3(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp7$3 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp6$3 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp7$3);
        /*AtsReturn*/return tmp6$3;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/foo_0();
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynload();
        mainats_void_0();
    }


}


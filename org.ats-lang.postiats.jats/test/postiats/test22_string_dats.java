
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test22_string_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", PtrkType.cType);
    }

    /*UserFunc*/static public SingletonValue foo_val_0(Ptrk arg0) {
        /*BlockNode*//*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue foo_var_1(Ptrk arg0) {
        /*BlockNode*//*AtsInsStore*/RefType.updateFromNonRefType(arg0, StringType.fromString("good"), PtrkType.cType);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref4 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp7 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Boolean tmp10 = /*DefinitionNode*/null;
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_val_0(StringType.fromString("abc"));
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref4, StringType.fromString("bcd"), PtrkType.cType);
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_val_0((Ptrk)RefType.cloneValue(tmpref4, PtrkType.cType));
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_var_1(/*AtsPmvPtrof*/RefType.ptrof(tmpref4));
        /*AtsInsStoreFltrecOfs*/tmp7.put("atslab$0", 1);
        /*AtsInsStoreFltrecOfs*/tmp7.put("atslab$1", StringType.fromString("def"));
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_val_0(/*AtsSelFltRec*/(Ptrk)tmp7.get("atslab$1"));
        /*AtsInsMove*/tmp10 = /*FuncCallNode*/CCompString.atspre_eq_string_string((Ptrk)RefType.cloneValue(tmpref4, PtrkType.cType), StringType.fromString("good"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp10, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test22_string.dats: 338(line=21, offs=12) -- 360(line=21, offs=34)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test22_string_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test22_string_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test22_string_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test22_string_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test22_string_056$dats__dynload();
        mainats_void_0();
    }


}


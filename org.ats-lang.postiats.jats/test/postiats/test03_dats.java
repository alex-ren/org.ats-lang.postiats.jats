
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test03_dats {
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
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref3 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Boolean tmp7 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp9 = /*DefinitionNode*/null;
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref3, StringType.fromString("abc"), PtrkType.cType);
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_val_0((Ptrk)RefType.cloneValue(tmpref3, PtrkType.cType));
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_var_1(/*AtsPmvPtrof*/RefType.ptrof(tmpref3));
        /*AtsInsMove*/tmp7 = /*FuncCallNode*/CCompString.atspre_eq_string_string((Ptrk)RefType.cloneValue(tmpref3, PtrkType.cType), StringType.fromString("good"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp7, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test03.dats: 256(line=14, offs=12) -- 278(line=14, offs=34)"));
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref3, StringType.fromString("bcd"), PtrkType.cType);
        /*AtsInsMove*/tmp9 = /*FuncCallNode*/CCompString.atspre_neq_string_string(StringType.fromString("bcd"), StringType.fromString("good"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp9, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test03.dats: 312(line=17, offs=12) -- 335(line=17, offs=35)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test03_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test03_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test03_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test03_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test03_056$dats__dynload();
        mainats_void_0();
    }


}


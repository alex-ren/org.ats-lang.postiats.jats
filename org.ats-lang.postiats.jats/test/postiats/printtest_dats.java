
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class printtest_dats {
    /*UserFunc*/static public SingletonValue foo_0(Ptrk arg0) {
        /*BlockNode*//*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Integer mainats_void_int() {
        /*BlockNode*//*DefinitionNode*/Integer tmpret1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref2 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Boolean tmp7 = /*DefinitionNode*/null;
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref2, StringType.fromString("123"), PtrkType.cType);
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_0((Ptrk)RefType.cloneValue(tmpref2, PtrkType.cType));
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref2, StringType.fromString("456"), PtrkType.cType);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string((Ptrk)RefType.cloneValue(tmpref2, PtrkType.cType));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMove*/tmp7 = /*FuncCallNode*/CCompString.atspre_eq_string_string((Ptrk)RefType.cloneValue(tmpref2, PtrkType.cType), StringType.fromString("456"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp7, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/printtest.dats: 186(line=12, offs=13) -- 206(line=12, offs=33)"));
        /*AtsInsMove*/tmpret1 = 0;
        /*AtsReturn*/return tmpret1;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$printtest_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$printtest_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$printtest_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$printtest_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$printtest_056$dats__dynload();
        mainats_void_int();
    }


}



package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test05_dats {
    /*UserFunc*/static public Integer foo_int_0(Integer arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = arg0;
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref2 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp5 = /*DefinitionNode*/null;
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref2, 7, IntType.cType);
        /*AtsInsMove*/tmp3 = /*FuncCallNode*/foo_int_0(6);
        /*AtsInsMove*/tmp4 = (Integer)RefType.cloneValue(tmpref2, IntType.cType);
        /*AtsInsMove*/tmp5 = /*FuncCallNode*/foo_int_0(tmp4);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test05_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test05_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test05_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test05_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test05_056$dats__dynload();
        mainats_void_0();
    }


}



package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test04_dats {
    /*UserFunc*/static public Integer foo1_0(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = 3;
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Ptrk foo2_1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpret1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret1 = arg0;
        /*AtsReturn*/return tmpret1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmpref3 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp5 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp4 = /*FuncCallNode*/foo1_0((Ptrk)RefType.cloneValue(tmpref3, PtrkType.cType));
        /*AtsInsMove*/tmp5 = /*FuncCallNode*/foo2_1((Ptrk)RefType.cloneValue(tmpref3, PtrkType.cType));
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref3, tmp5, PtrkType.cType);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test04_056$dats__dynload();
        mainats_void_0();
    }


}


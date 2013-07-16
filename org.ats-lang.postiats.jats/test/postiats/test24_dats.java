
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test24_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", CharType.cType);
    }

    /*DefinitionNode*/static public /*DefinitionNode*/Integer statmp1 = /*DefinitionNode*/null;;

    /*UserFunc*/static public Integer init_0(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret0 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret0 = /*FuncCallNode*/CCompInteger.atspre_g0int_add_int(arg0, arg1);
        /*AtsReturn*/return tmpret0;
    }

    /*UserFunc*/static public Integer foo_bt_var_2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmpret5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp6 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp7 = /*DefinitionNode*/null;
        /*AtsInsMoveBoxrec*/tmp6 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp6.put("atslab$0", statmp1);
        /*AtsInsStoreBoxrecOfs*/tmp6.put("atslab$1", 'c');
        /*AtsInsStore*/RefType.updateFromNonRefType(arg0, tmp6, BoxedType.cType);
        /*AtsInsMove*/tmp7 = /*FuncCallNode*/CCompChar.atspre_eq_int0_char0(/*AtsSelBoxRec*/(Integer)((Map<String, Object>)RefType.getValue(arg0, BoxedType.cType)).get("atslab$0"), 'c');
        /*IfNode*/if (tmp7 == true) {
            /*BlockNode*//*AtsInsMove*/tmpret5 = 4;
        } else {
            /*BlockNode*//*AtsInsMove*/tmpret5 = 3;
        }
        /*AtsReturn*/return tmpret5;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$4$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp11$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp12$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp11$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp12$1);
        /*AtsReturn*/return tmp11$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Boolean tmp10 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp15 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp10 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$4$1(statmp1, 7);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp10, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test24.dats: 281(line=16, offs=11) -- 297(line=16, offs=27)"));
        /*AtsInsMove*/tmp15 = /*FuncCallNode*/CCompInteger.atspre_g0int_add_int(statmp1, 3);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("hello world"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test24_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test24_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test24_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test24_056$dats__dynloadflag = 1;
            /*AtsInsMove*/statmp1 = /*FuncCallNode*/init_0(3, 4);
            /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("hello global"));
            /*AtsInsMoveVoid*//*FuncCallNode*/CCompInteger.atspre_print_int(statmp1);
            /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test24_056$dats__dynload();
        mainats_void_0();
    }


}


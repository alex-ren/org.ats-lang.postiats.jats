
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test23_array_dats {
    /*UserFunc*/static public SingletonValue foo_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp1 = /*DefinitionNode*/null;
        /*DefinitionNode*/ArrPsz tmp2 = /*DefinitionNode*/ArrPszType.cType.createNormalDefault();
        /*DefinitionNode*/Ptrk tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp4 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp24 = /*DefinitionNode*/null;
        /*AtsInsStoreArrpszPtr*/tmp2.init(3, IntType.cType);
        /*AtsInsMoveArrpszPtr*/tmp3 = ((ArrPsz) tmp2).getPtr();
        /*AtsInsPMove*/tmp3.update(3, IntType.cType);
        /*AtsInsUpdatePtrInc*/tmp3 = Ptrk.createPtrkOffset(tmp3, 4);  
        /*AtsInsPMove*/tmp3.update(4, IntType.cType);
        /*AtsInsUpdatePtrInc*/tmp3 = Ptrk.createPtrkOffset(tmp3, 4);  
        /*AtsInsPMove*/tmp3.update(5, IntType.cType);
        /*AtsInsMove*/tmp1 = /*FuncCallNode*/CCompArrayPtr.atspre_arrayptr_make_arrpsz(tmp2);
        /*AtsInsMove*/tmp4 = /*FuncCallNode*/ATSLIB_056$prelude_arrayptr_get_at_gint$1$1(tmp1, 0);
        /*AtsInsMove*/tmp24 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$11$1(tmp4, 3);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp24, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test23_array.dats: 174(line=12, offs=10) -- 190(line=12, offs=26)"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompArrayPtr.atspre_arrayptr_free(tmp1);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_arrayptr_get_at_gint$1$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp5$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp6$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp6$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_add_gint$3$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMove*/tmp5$1 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1(tmp6$1);
        /*AtsReturn*/return tmp5$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_add_gint$3$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp9$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp10$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp10$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_add_gint$5$1(arg0, arg1);
        /*AtsInsMove*/tmp9$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp10$1;
        /*AtsReturn*/return tmp9$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_add_gint$5$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp13$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp14$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp15$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp15$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp14$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint_mul_size(tmp15$1, /*AtsPmvSizeofNode*/4);
        /*AtsInsMove*/tmp13$1 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, tmp14$1);
        /*AtsReturn*/return tmp13$1;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp19$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp20$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp20$1 = (Integer)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, IntType.cType);
        /*AtsInsMove*/tmp19$1 = tmp20$1;
        /*AtsReturn*/return tmp19$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$11$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp25$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp26$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp26$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp25$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp26$1);
        /*AtsReturn*/return tmp25$1;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/foo_0();
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynload();
        mainats_void_0();
    }


}


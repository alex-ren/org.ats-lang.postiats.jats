
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test15_dats {
    /*UserFunc*/static public SingletonValue foo_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp1 = /*DefinitionNode*/null;
        /*DefinitionNode*/ArrPsz tmp2 = /*DefinitionNode*/ArrPszType.cType.createNormalDefault();
        /*DefinitionNode*/Ptrk tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp5 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp10 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp30 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp33 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp44 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp47 = /*DefinitionNode*/null;
        /*AtsInsStoreArrpszPtr*/tmp2.init(3, IntType.cType);
        /*AtsInsMoveArrpszPtr*/tmp3 = ((ArrPsz) tmp2).getPtr();
        /*AtsInsPMove*/tmp3.update(3, IntType.cType);
        /*AtsInsUpdatePtrInc*/tmp3 = Ptrk.createPtrkOffset(tmp3, 4);  
        /*AtsInsPMove*/tmp3.update(4, IntType.cType);
        /*AtsInsUpdatePtrInc*/tmp3 = Ptrk.createPtrkOffset(tmp3, 4);  
        /*AtsInsPMove*/tmp3.update(5, IntType.cType);
        /*AtsInsMove*/tmp1 = /*FuncCallNode*/CCompArrayPtr.atspre_arrayptr_make_arrpsz(tmp2);
        /*AtsInsMove*/tmp10 = /*FuncCallNode*/ATSLIB_056$prelude_arrayptr_get_at_gint$5$1(tmp1, 0);
        /*AtsInsMove*/tmp5 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$1$1(tmp10, 3);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp5, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test15.dats: 118(line=9, offs=10) -- 137(line=9, offs=29)"));
        /*AtsInsMove*/tmp33 = /*FuncCallNode*/ATSLIB_056$prelude_arrayptr_get_at_gint$5$2(tmp1, 1);
        /*AtsInsMove*/tmp30 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$1$2(tmp33, 4);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp30, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test15.dats: 148(line=10, offs=10) -- 167(line=10, offs=29)"));
        /*AtsInsMove*/tmp47 = /*FuncCallNode*/ATSLIB_056$prelude_arrayptr_get_at_gint$5$3(tmp1, 2);
        /*AtsInsMove*/tmp44 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$1$3(tmp47, 5);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp44, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test15.dats: 178(line=11, offs=10) -- 197(line=11, offs=29)"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompArrayPtr.atspre_arrayptr_free(tmp1);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$1$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp6$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp7$1);
        /*AtsReturn*/return tmp6$1;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_arrayptr_get_at_gint$5$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp11$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp12$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_add_gint$7$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMove*/tmp11$1 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$13$1(tmp12$1);
        /*AtsReturn*/return tmp11$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_add_gint$7$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp15$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp16$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp16$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_add_gint$9$1(arg0, arg1);
        /*AtsInsMove*/tmp15$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp16$1;
        /*AtsReturn*/return tmp15$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_add_gint$9$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp19$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp20$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp21$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp21$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp20$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint_mul_size(tmp21$1, /*AtsPmvSizeofNode*/4);
        /*AtsInsMove*/tmp19$1 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, tmp20$1);
        /*AtsReturn*/return tmp19$1;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_056$unsafe_ptr0_get$13$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp25$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp26$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp26$1 = (Integer)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, IntType.cType);
        /*AtsInsMove*/tmp25$1 = tmp26$1;
        /*AtsReturn*/return tmp25$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$1$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp7$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp6$2 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp7$2);
        /*AtsReturn*/return tmp6$2;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_arrayptr_get_at_gint$5$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp11$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp12$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$2 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_add_gint$7$2(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMove*/tmp11$2 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$13$2(tmp12$2);
        /*AtsReturn*/return tmp11$2;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_add_gint$7$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp15$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp16$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp16$2 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_add_gint$9$2(arg0, arg1);
        /*AtsInsMove*/tmp15$2 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp16$2;
        /*AtsReturn*/return tmp15$2;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_add_gint$9$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp19$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp20$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp21$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp21$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp20$2 = /*FuncCallNode*/CCompInteger.atspre_g0uint_mul_size(tmp21$2, /*AtsPmvSizeofNode*/4);
        /*AtsInsMove*/tmp19$2 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, tmp20$2);
        /*AtsReturn*/return tmp19$2;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_056$unsafe_ptr0_get$13$2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp25$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp26$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp26$2 = (Integer)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, IntType.cType);
        /*AtsInsMove*/tmp25$2 = tmp26$2;
        /*AtsReturn*/return tmp25$2;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$1$3(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp6$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp7$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp7$3 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp6$3 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp7$3);
        /*AtsReturn*/return tmp6$3;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_arrayptr_get_at_gint$5$3(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Integer tmp11$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp12$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$3 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_add_gint$7$3(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMove*/tmp11$3 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$13$3(tmp12$3);
        /*AtsReturn*/return tmp11$3;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_add_gint$7$3(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp15$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp16$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp16$3 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_add_gint$9$3(arg0, arg1);
        /*AtsInsMove*/tmp15$3 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp16$3;
        /*AtsReturn*/return tmp15$3;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_add_gint$9$3(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp19$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp20$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp21$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp21$3 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp20$3 = /*FuncCallNode*/CCompInteger.atspre_g0uint_mul_size(tmp21$3, /*AtsPmvSizeofNode*/4);
        /*AtsInsMove*/tmp19$3 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, tmp20$3);
        /*AtsReturn*/return tmp19$3;
    }

    /*UserFunc*/static public Integer ATSLIB_056$prelude_056$unsafe_ptr0_get$13$3(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Integer tmp25$3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp26$3 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp26$3 = (Integer)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, IntType.cType);
        /*AtsInsMove*/tmp25$3 = tmp26$3;
        /*AtsReturn*/return tmp25$3;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/foo_0();
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test15_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test15_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test15_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test15_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test15_056$dats__dynload();
        mainats_void_0();
    }


}



package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test19_dats {
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

    /*UserFunc*/static public SingletonValue foo_0() {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp1 = /*DefinitionNode*/null;
        /*DefinitionNode*/ArrPsz tmp2 = /*DefinitionNode*/ArrPszType.cType.createNormalDefault();
        /*DefinitionNode*/Ptrk tmp3 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp4 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Map<String, Object> tmp5 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Map<String, Object> tmp6 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Map<String, Object> tmp37 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Ptrk tmpref39 = /*DefinitionNode*/postiats_tyrec_1.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp40 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Boolean tmp42 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp48 = /*DefinitionNode*/null;
        /*AtsInsStoreArrpszPtr*/tmp2.init(2, postiats_tyrec_0);
        /*AtsInsMoveArrpszPtr*/tmp3 = ((ArrPsz) tmp2).getPtr();
        /*AtsInsStoreFltrecOfs*/tmp4.put("atslab$0", 1);
        /*AtsInsStoreFltrecOfs*/tmp4.put("atslab$1", 1);
        /*AtsInsPMove*/tmp3.update(tmp4, postiats_tyrec_0);
        /*AtsInsUpdatePtrInc*/tmp3 = Ptrk.createPtrkOffset(tmp3, 8);  
        /*AtsInsStoreFltrecOfs*/tmp5.put("atslab$0", 2);
        /*AtsInsStoreFltrecOfs*/tmp5.put("atslab$1", 2);
        /*AtsInsPMove*/tmp3.update(tmp5, postiats_tyrec_0);
        /*AtsInsMove*/tmp1 = /*FuncCallNode*/CCompArrayPtr.atspre_arrayptr_make_arrpsz(tmp2);
        /*AtsInsMove*/tmp6 = /*FuncCallNode*/ATSLIB_056$prelude_arrayptr_get_at_gint$1$1(tmp1, 0);
        /*AtsInsStoreFltrecOfs*/tmp37.put("atslab$0", 3);
        /*AtsInsStoreFltrecOfs*/tmp37.put("atslab$1", 3);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_arrayptr_set_at_gint$11$1(tmp1, 0, tmp37);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompArrayPtr.atspre_arrayptr_free(tmp1);
        /*AtsInsStoreFltrecOfs*/tmp40.put("atslab$0", 2);
        /*AtsInsStoreFltrecOfs*/tmp40.put("atslab$1", 3);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref39, 1, "atslab$0", postiats_tyrec_1);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref39, tmp40, "atslab$1", postiats_tyrec_1);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(tmpref39, "atslab$0", postiats_tyrec_1), 3, IntType.cType);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref39, "atslab$1", postiats_tyrec_1), "atslab$0", postiats_tyrec_0), 4, IntType.cType);
        /*AtsInsMove*/tmp42 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$17$1((Integer)RefType.cloneValue(RefType.SelFltrecOfs(tmpref39, "atslab$0", postiats_tyrec_1), IntType.cType), 3);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp42, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test19.dats: 309(line=19, offs=10) -- 328(line=19, offs=29)"));
        /*AtsInsMove*/tmp48 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$17$2((Integer)RefType.cloneValue(RefType.SelFltrecOfs(RefType.SelFltrecOfs(tmpref39, "atslab$1", postiats_tyrec_1), "atslab$0", postiats_tyrec_0), IntType.cType), 4);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp48, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test19.dats: 339(line=20, offs=10) -- 360(line=20, offs=31)"));
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Map<String, Object> ATSLIB_056$prelude_arrayptr_get_at_gint$1$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Map<String, Object> tmp7$1 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Ptrk tmp8$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp8$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_add_gint$3$1(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMove*/tmp7$1 = /*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1(tmp8$1);
        /*AtsReturn*/return tmp7$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_add_gint$3$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp11$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp12$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_add_gint$5$1(arg0, arg1);
        /*AtsInsMove*/tmp11$1 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp12$1;
        /*AtsReturn*/return tmp11$1;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_add_gint$5$1(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp15$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp16$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp17$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp17$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp16$1 = /*FuncCallNode*/CCompInteger.atspre_g0uint_mul_size(tmp17$1, /*AtsPmvSizeofNode*/8);
        /*AtsInsMove*/tmp15$1 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, tmp16$1);
        /*AtsReturn*/return tmp15$1;
    }

    /*UserFunc*/static public Map<String, Object> ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Map<String, Object> tmp21$1 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Map<String, Object> tmp22$1 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*AtsInsMove*/tmp22$1 = (Map<String, Object>)RefType.cloneValue(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, postiats_tyrec_0);
        /*AtsInsMove*/tmp21$1 = tmp22$1;
        /*AtsReturn*/return tmp21$1;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_arrayptr_set_at_gint$11$1(Ptrk arg0, Integer arg1, Map<String, Object> arg2) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp27$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp27$1 = /*FuncCallNode*/ATSLIB_056$prelude_ptr1_add_gint$3$2(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1);
        /*AtsInsMoveVoid*//*FuncCallNode*/ATSLIB_056$prelude_056$unsafe_ptr0_set$15$1(tmp27$1, arg2);
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr1_add_gint$3$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp11$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmp12$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp12$2 = /*FuncCallNode*/ATSLIB_056$prelude_ptr0_add_gint$5$2(arg0, arg1);
        /*AtsInsMove*/tmp11$2 = /*AtsPmvCastFn*//*castfn_currently_no_op*/tmp12$2;
        /*AtsReturn*/return tmp11$2;
    }

    /*UserFunc*/static public Ptrk ATSLIB_056$prelude_ptr0_add_gint$5$2(Ptrk arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Ptrk tmp15$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp16$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp17$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp17$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2uint_int_size(arg1);
        /*AtsInsMove*/tmp16$2 = /*FuncCallNode*/CCompInteger.atspre_g0uint_mul_size(tmp17$2, /*AtsPmvSizeofNode*/8);
        /*AtsInsMove*/tmp15$2 = /*FuncCallNode*/CCompPointer.atspre_add_ptr0_bsz(arg0, tmp16$2);
        /*AtsReturn*/return tmp15$2;
    }

    /*UserFunc*/static public SingletonValue ATSLIB_056$prelude_056$unsafe_ptr0_set$15$1(Ptrk arg0, Map<String, Object> arg1) {
        /*BlockNode*//*AtsInsStore*/RefType.updateFromNonRefType(/*AtsPmvCastFn*//*castfn_currently_no_op*/arg0, arg1, postiats_tyrec_0);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$17$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp43$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp44$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp44$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp43$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp44$1);
        /*AtsReturn*/return tmp43$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$17$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp43$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp44$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp44$2 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp43$2 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp44$2);
        /*AtsReturn*/return tmp43$2;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/foo_0();
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test19_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test19_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test19_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test19_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test19_056$dats__dynload();
        mainats_void_0();
    }


}


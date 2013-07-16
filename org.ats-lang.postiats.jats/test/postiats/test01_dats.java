
package postiats;                                                   

import org.ats_lang.postiats.jats.type.*;
import org.ats_lang.postiats.jats.value.*;
import org.ats_lang.postiats.jats.ccomp.*;   

import java.util.HashMap;
import java.util.Map;

public class test01_dats {
    /*StructType*/static StructType postiats_tyrec_0 = new StructType("postiats_tyrec_0");
    static {
        /*StructType*/postiats_tyrec_0.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_0.addMember("atslab$1", DoubleType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_1 = new StructType("postiats_tyrec_1");
    static {
        /*StructType*/postiats_tyrec_1.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_1.addMember("atslab$1", PtrkType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_2 = new StructType("postiats_tyrec_2");
    static {
        /*StructType*/postiats_tyrec_2.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_2.addMember("atslab$1", IntType.cType);
    }

    /*StructType*/static StructType postiats_tyrec_3 = new StructType("postiats_tyrec_3");
    static {
        /*StructType*/postiats_tyrec_3.addMember("atslab$0", IntType.cType);
        /*StructType*/postiats_tyrec_3.addMember("atslab$1", CharType.cType);
    }

    /*UserFunc*/static public SingletonValue foo_bt_val_0(Map<String, Object> arg0) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("int is "));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompInteger.atspre_print_int(/*AtsSelBoxRec*/(Integer)arg0.get("atslab$0"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue foo_ft_val_1(Map<String, Object> arg0) {
        /*BlockNode*//*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("int is "));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompInteger.atspre_print_int(/*AtsSelFltRec*/(Integer)arg0.get("atslab$0"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue foo_bt_var_2(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Map<String, Object> tmp9 = /*DefinitionNode*/null;
        /*AtsInsMoveBoxrec*/tmp9 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp9.put("atslab$0", 52);
        /*AtsInsStoreBoxrecOfs*/tmp9.put("atslab$1", 'c');
        /*AtsInsStore*/RefType.updateFromNonRefType(arg0, tmp9, BoxedType.cType);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue foo_ft_var_3(Ptrk arg0) {
        /*BlockNode*//*DefinitionNode*/Map<String, Object> tmp11 = /*DefinitionNode*/postiats_tyrec_1.createNormalDefault();
        /*AtsInsStoreFltrecOfs*/tmp11.put("atslab$0", 72);
        /*AtsInsStoreFltrecOfs*/tmp11.put("atslab$1", StringType.fromString("cde"));
        /*AtsInsStore*/RefType.updateFromNonRefType(arg0, tmp11, postiats_tyrec_1);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(arg0, "atslab$1", postiats_tyrec_1), StringType.fromString("def"), PtrkType.cType);
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public Integer foo_int_4() {
        /*BlockNode*//*DefinitionNode*/Integer tmpret12 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmpret12 = 3;
        /*AtsReturn*/return tmpret12;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$7$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp29$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp30$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp30$1 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp29$1 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp30$1);
        /*AtsReturn*/return tmp29$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$11$1(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp36$1 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp37$1 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp37$1 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp36$1 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp37$1);
        /*AtsReturn*/return tmp36$1;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g1int_int$7$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp29$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp30$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp30$2 = /*FuncCallNode*/CCompInteger.atspre_g1int2int_int_int(arg1);
        /*AtsInsMove*/tmp29$2 = /*FuncCallNode*/CCompInteger.atspre_g1int_eq_int(arg0, tmp30$2);
        /*AtsReturn*/return tmp29$2;
    }

    /*UserFunc*/static public Boolean ATSLIB_056$prelude_eq_g0int_int$11$2(Integer arg0, Integer arg1) {
        /*BlockNode*//*DefinitionNode*/Boolean tmp36$2 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp37$2 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp37$2 = /*FuncCallNode*/CCompInteger.atspre_g0int2int_int_int(arg1);
        /*AtsInsMove*/tmp36$2 = /*FuncCallNode*/CCompInteger.atspre_g0int_eq_int(arg0, tmp37$2);
        /*AtsReturn*/return tmp36$2;
    }

    /*UserFunc*/static public SingletonValue mainats_void_0() {
        /*BlockNode*//*DefinitionNode*/Integer tmp14 = /*DefinitionNode*/null;
        /*DefinitionNode*/Integer tmp15 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref16 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Ptrk tmpref17 = /*DefinitionNode*/IntType.cType.createRefDefault();
        /*DefinitionNode*/Integer tmp18 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp19 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp20 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp22 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Map<String, Object> tmp23 = /*DefinitionNode*/postiats_tyrec_0.createNormalDefault();
        /*DefinitionNode*/Ptrk tmpref25 = /*DefinitionNode*/PtrkType.cType.createRefDefault();
        /*DefinitionNode*/Map<String, Object> tmp26 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp28 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp35 = /*DefinitionNode*/null;
        /*DefinitionNode*/Ptrk tmpref40 = /*DefinitionNode*/postiats_tyrec_1.createRefDefault();
        /*DefinitionNode*/Boolean tmp42 = /*DefinitionNode*/null;
        /*DefinitionNode*/Map<String, Object> tmp45 = /*DefinitionNode*/postiats_tyrec_1.createNormalDefault();
        /*DefinitionNode*/Boolean tmp47 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp50 = /*DefinitionNode*/null;
        /*DefinitionNode*/Boolean tmp54 = /*DefinitionNode*/null;
        /*AtsInsMove*/tmp14 = /*FuncCallNode*/foo_int_4();
        /*AtsInsMove*/tmp15 = /*FuncCallNode*/CCompInteger.atspre_g0int_add_int(tmp14, 1);
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref16, /*FuncCallNode*/foo_int_4(), IntType.cType);
        /*AtsInsMove*/RefType.updateFromNonRefType(tmpref17, /*FuncCallNode*/CCompInteger.atspre_g0int_add_int((Integer)RefType.cloneValue(tmpref16, IntType.cType), 1), IntType.cType);
        /*AtsInsMove*/tmp18 = /*FuncCallNode*/CCompInteger.atspre_g0int_add_int((Integer)RefType.cloneValue(tmpref17, IntType.cType), 2);
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref17, tmp18, IntType.cType);
        /*AtsInsMoveBoxrec*/tmp19 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp19.put("atslab$0", 0);
        /*AtsInsStoreBoxrecOfs*/tmp19.put("atslab$1", 1);
        /*AtsInsMoveBoxrec*/tmp20 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp20.put("atslab$0", 1);
        /*AtsInsStoreBoxrecOfs*/tmp20.put("atslab$1", 2);
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_bt_val_0(tmp20);
        /*AtsInsStoreFltrecOfs*/tmp22.put("atslab$0", 2);
        /*AtsInsStoreFltrecOfs*/tmp22.put("atslab$1", 0.1);
        /*AtsInsStoreFltrecOfs*/tmp23.put("atslab$0", 3);
        /*AtsInsStoreFltrecOfs*/tmp23.put("atslab$1", 1.2);
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_ft_val_1(tmp23);
        /*AtsInsMoveBoxrec*/RefType.updateFromNonRefType(tmpref25, new HashMap<String, Object>(), BoxedType.cType);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref25.getValue(BoxedType.cType)).put("atslab$0", 5);
        /*AtsInsStoreBoxrecOfs*/((Map<String, Object>)tmpref25.getValue(BoxedType.cType)).put("atslab$1", 'a');
        /*AtsInsMoveBoxrec*/tmp26 = new HashMap<String, Object>();
        /*AtsInsStoreBoxrecOfs*/tmp26.put("atslab$0", 51);
        /*AtsInsStoreBoxrecOfs*/tmp26.put("atslab$1", 'b');
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref25, tmp26, postiats_tyrec_3);
        /*AtsInsMove*/tmp28 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$7$1(/*AtsSelBoxRec*/(Integer)((Map<String, Object>)RefType.getValue(tmpref25, BoxedType.cType)).get("atslab$0"), 51);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp28, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test01.dats: 894(line=53, offs=12) -- 920(line=53, offs=38)"));
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_bt_var_2(/*AtsPmvPtrof*/RefType.ptrof(tmpref25));
        /*AtsInsMove*/tmp35 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$11$1(/*AtsSelBoxRec*/(Integer)((Map<String, Object>)RefType.getValue(tmpref25, BoxedType.cType)).get("atslab$0"), 52);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp35, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test01.dats: 1007(line=57, offs=12) -- 1033(line=57, offs=38)"));
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref40, 7, "atslab$0", postiats_tyrec_1);
        /*AtsInsStoreFltrecOfs*/RefType.updateFltrecOfs(tmpref40, StringType.fromString("abc"), "atslab$1", postiats_tyrec_1);
        /*AtsInsStore*/RefType.updateFromNonRefType(RefType.SelFltrecOfs(tmpref40, "atslab$0", postiats_tyrec_1), 70, IntType.cType);
        /*AtsInsMove*/tmp42 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g1int_int$7$2((Integer)RefType.cloneValue(RefType.SelFltrecOfs(tmpref40, "atslab$0", postiats_tyrec_1), IntType.cType), 70);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool1(tmp42, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test01.dats: 1144(line=62, offs=12) -- 1170(line=62, offs=38)"));
        /*AtsInsStoreFltrecOfs*/tmp45.put("atslab$0", 71);
        /*AtsInsStoreFltrecOfs*/tmp45.put("atslab$1", StringType.fromString("bcd"));
        /*AtsInsStore*/RefType.updateFromNonRefType(tmpref40, tmp45, postiats_tyrec_1);
        /*AtsInsMove*/tmp47 = /*FuncCallNode*/CCompString.atspre_eq_string_string((Ptrk)RefType.cloneValue(RefType.SelFltrecOfs(tmpref40, "atslab$1", postiats_tyrec_1), PtrkType.cType), StringType.fromString("bcd"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp47, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test01.dats: 1222(line=65, offs=13) -- 1251(line=65, offs=42)"));
        /*AtsInsMoveVoid*//*FuncCallNode*/foo_ft_var_3(/*AtsPmvPtrof*/RefType.ptrof(tmpref40));
        /*AtsInsMove*/tmp50 = /*FuncCallNode*/ATSLIB_056$prelude_eq_g0int_int$11$2((Integer)RefType.cloneValue(RefType.SelFltrecOfs(tmpref40, "atslab$0", postiats_tyrec_1), IntType.cType), 72);
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp50, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test01.dats: 1303(line=68, offs=13) -- 1329(line=68, offs=39)"));
        /*AtsInsMove*/tmp54 = /*FuncCallNode*/CCompString.atspre_eq_string_string((Ptrk)RefType.cloneValue(RefType.SelFltrecOfs(tmpref40, "atslab$1", postiats_tyrec_1), PtrkType.cType), StringType.fromString("def"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_assert_errmsg_bool0(tmp54, StringType.fromString("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test01.dats: 1343(line=69, offs=13) -- 1372(line=69, offs=42)"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompString.atspre_print_string(StringType.fromString("done"));
        /*AtsInsMoveVoid*//*FuncCallNode*/CCompBasics.atspre_print_newline();
        /*AtsInsMoveVoid*/
        /*AtsReturn*/return SingletonValue.VOID;
    }

    /*UserFunc*/static public SingletonValue _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test01_056$dats__dynload() {
        /*BlockNode*//*ATSdynload0*/Integer _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test01_056$dats__dynloadflag = 0;
        /*IfNode*/if (/*AtsCkIseqz*/(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test01_056$dats__dynloadflag == 0) == true) {
            /*BlockNode*//*AtsInsMove*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test01_056$dats__dynloadflag = 1;
        } 
        /*AtsReturn*/return SingletonValue.VOID;
    }

    static public void main(String [] args) {
        /*FuncCallNode*/_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test01_056$dats__dynload();
        mainats_void_0();
    }


}


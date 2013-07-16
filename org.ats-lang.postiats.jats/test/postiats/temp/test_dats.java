package postiats;

import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.CharValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.ULIntValue;
import org.ats_lang.postiats.jats.value.SizeValue;
import org.ats_lang.postiats.jats.value.DoubleValue;
import org.ats_lang.postiats.jats.value.StringValue;
import org.ats_lang.postiats.jats.ccomp.CCompCompositeValue;

import org.ats_lang.postiats.jats.ccomp.CCompTypedefs;

import org.ats_lang.postiats.jats.ccomp.CCompArrayPtr;
import org.ats_lang.postiats.jats.ccomp.CCompBasics;
import org.ats_lang.postiats.jats.ccomp.CCompFloat;
import org.ats_lang.postiats.jats.ccomp.CCompInteger;
import org.ats_lang.postiats.jats.ccomp.CCompString;


public class test_dats {
    static public CCompTypedefs.atstype_arrpsz tmp0 = CCompTypedefs.atstype_arrpsz.m_type.createDefault();;

    static public CCompTypedefs.CPtrValue tmp1 = CCompTypedefs.CPtrValue.m_type.createDefault();;

    static public SizeValue tmp2 = SizeValue.m_type.createDefault();;

    static public CCompTypedefs.CPtrValue tmp3 = CCompTypedefs.CPtrValue.m_type.createDefault();;

    static public DoubleValue tmp6 = DoubleValue.m_type.createDefault();;

    static public DoubleValue tmp28 = DoubleValue.m_type.createDefault();;

    static public DoubleValue tmp41 = DoubleValue.m_type.createDefault();;

    static public DoubleValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$arrayptr_056$sats__arrayptr_get_at_gint$0$1(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        DoubleValue tmp7$1 = DoubleValue.m_type.createDefault();
        CCompTypedefs.CPtrValue tmp8$1 = CCompTypedefs.CPtrValue.m_type.createDefault();
        tmp8$1.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr1_add_gint$2$1(/*castfn_currently_no_op*/arg0.deepcopy(), arg1.deepcopy()));
        tmp7$1.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$unsafe_056$sats__ptr0_get$8$1(tmp8$1.deepcopy()));
        return tmp7$1.deepcopy();
    }

    static public CCompTypedefs.CPtrValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr1_add_gint$2$1(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        CCompTypedefs.CPtrValue tmp11$1 = CCompTypedefs.CPtrValue.m_type.createDefault();
        CCompTypedefs.CPtrValue tmp12$1 = CCompTypedefs.CPtrValue.m_type.createDefault();
        tmp12$1.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr0_add_gint$4$1(arg0.deepcopy(), arg1.deepcopy()));
        tmp11$1.copyfrom(/*castfn_currently_no_op*/tmp12$1);
        return tmp11$1.deepcopy();
    }

    static public CCompTypedefs.CPtrValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr0_add_gint$4$1(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        CCompTypedefs.CPtrValue tmp15$1 = CCompTypedefs.CPtrValue.m_type.createDefault();
        SizeValue tmp16$1 = SizeValue.m_type.createDefault();
        SizeValue tmp17$1 = SizeValue.m_type.createDefault();
        tmp17$1.copyfrom(CCompInteger.atspre_g0int2uint_int_size(arg1.deepcopy()));
        tmp16$1.copyfrom(CCompInteger.atspre_g0uint_mul_size(tmp17$1.deepcopy(), SizeValue.create(DoubleValue.m_type.getSize()).deepcopy()));
        tmp15$1.copyfrom(CCompArrayPtr.atspre_ptr0_add_bytesize(arg0.deepcopy(), tmp16$1.deepcopy()));
        return tmp15$1.deepcopy();
    }

    static public DoubleValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$unsafe_056$sats__ptr0_get$8$1(CCompTypedefs.CPtrValue arg0) {
        DoubleValue tmp21$1 = DoubleValue.m_type.createDefault();
        DoubleValue tmp22$1 = DoubleValue.m_type.createDefault();
        tmp22$1.copyfrom(((DoubleValue) /*castfn_currently_no_op*/arg0.deRef()));
        tmp21$1.copyfrom(tmp22$1);
        return tmp21$1.deepcopy();
    }

    static public DoubleValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$arrayptr_056$sats__arrayptr_get_at_gint$0$2(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        DoubleValue tmp7$2 = DoubleValue.m_type.createDefault();
        CCompTypedefs.CPtrValue tmp8$2 = CCompTypedefs.CPtrValue.m_type.createDefault();
        tmp8$2.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr1_add_gint$2$2(/*castfn_currently_no_op*/arg0.deepcopy(), arg1.deepcopy()));
        tmp7$2.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$unsafe_056$sats__ptr0_get$8$2(tmp8$2.deepcopy()));
        return tmp7$2.deepcopy();
    }

    static public CCompTypedefs.CPtrValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr1_add_gint$2$2(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        CCompTypedefs.CPtrValue tmp11$2 = CCompTypedefs.CPtrValue.m_type.createDefault();
        CCompTypedefs.CPtrValue tmp12$2 = CCompTypedefs.CPtrValue.m_type.createDefault();
        tmp12$2.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr0_add_gint$4$2(arg0.deepcopy(), arg1.deepcopy()));
        tmp11$2.copyfrom(/*castfn_currently_no_op*/tmp12$2);
        return tmp11$2.deepcopy();
    }

    static public CCompTypedefs.CPtrValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr0_add_gint$4$2(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        CCompTypedefs.CPtrValue tmp15$2 = CCompTypedefs.CPtrValue.m_type.createDefault();
        SizeValue tmp16$2 = SizeValue.m_type.createDefault();
        SizeValue tmp17$2 = SizeValue.m_type.createDefault();
        tmp17$2.copyfrom(CCompInteger.atspre_g0int2uint_int_size(arg1.deepcopy()));
        tmp16$2.copyfrom(CCompInteger.atspre_g0uint_mul_size(tmp17$2.deepcopy(), SizeValue.create(DoubleValue.m_type.getSize()).deepcopy()));
        tmp15$2.copyfrom(CCompArrayPtr.atspre_ptr0_add_bytesize(arg0.deepcopy(), tmp16$2.deepcopy()));
        return tmp15$2.deepcopy();
    }

    static public DoubleValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$unsafe_056$sats__ptr0_get$8$2(CCompTypedefs.CPtrValue arg0) {
        DoubleValue tmp21$2 = DoubleValue.m_type.createDefault();
        DoubleValue tmp22$2 = DoubleValue.m_type.createDefault();
        tmp22$2.copyfrom(((DoubleValue) /*castfn_currently_no_op*/arg0.deRef()));
        tmp21$2.copyfrom(tmp22$2);
        return tmp21$2.deepcopy();
    }

    static public DoubleValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$arrayptr_056$sats__arrayptr_get_at_gint$0$3(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        DoubleValue tmp7$3 = DoubleValue.m_type.createDefault();
        CCompTypedefs.CPtrValue tmp8$3 = CCompTypedefs.CPtrValue.m_type.createDefault();
        tmp8$3.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr1_add_gint$2$3(/*castfn_currently_no_op*/arg0.deepcopy(), arg1.deepcopy()));
        tmp7$3.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$unsafe_056$sats__ptr0_get$8$3(tmp8$3.deepcopy()));
        return tmp7$3.deepcopy();
    }

    static public CCompTypedefs.CPtrValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr1_add_gint$2$3(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        CCompTypedefs.CPtrValue tmp11$3 = CCompTypedefs.CPtrValue.m_type.createDefault();
        CCompTypedefs.CPtrValue tmp12$3 = CCompTypedefs.CPtrValue.m_type.createDefault();
        tmp12$3.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr0_add_gint$4$3(arg0.deepcopy(), arg1.deepcopy()));
        tmp11$3.copyfrom(/*castfn_currently_no_op*/tmp12$3);
        return tmp11$3.deepcopy();
    }

    static public CCompTypedefs.CPtrValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$pointer_056$sats__ptr0_add_gint$4$3(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        CCompTypedefs.CPtrValue tmp15$3 = CCompTypedefs.CPtrValue.m_type.createDefault();
        SizeValue tmp16$3 = SizeValue.m_type.createDefault();
        SizeValue tmp17$3 = SizeValue.m_type.createDefault();
        tmp17$3.copyfrom(CCompInteger.atspre_g0int2uint_int_size(arg1.deepcopy()));
        tmp16$3.copyfrom(CCompInteger.atspre_g0uint_mul_size(tmp17$3.deepcopy(), SizeValue.create(DoubleValue.m_type.getSize()).deepcopy()));
        tmp15$3.copyfrom(CCompArrayPtr.atspre_ptr0_add_bytesize(arg0.deepcopy(), tmp16$3.deepcopy()));
        return tmp15$3.deepcopy();
    }

    static public DoubleValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$unsafe_056$sats__ptr0_get$8$3(CCompTypedefs.CPtrValue arg0) {
        DoubleValue tmp21$3 = DoubleValue.m_type.createDefault();
        DoubleValue tmp22$3 = DoubleValue.m_type.createDefault();
        tmp22$3.copyfrom(((DoubleValue) /*castfn_currently_no_op*/arg0.deRef()));
        tmp21$3.copyfrom(tmp22$3);
        return tmp21$3.deepcopy();
    }

    static public IntValue mainats() {
        tmp0.size.copyfrom(IntValue.create(3));
        tmp0.ptr.m_elesz = DoubleValue.m_type.getSize();
        tmp0.ptr.m_arr = new DoubleValue [SizeValue.castFromV(IntValue.create(3)).getContent()];
        for (int i = 0; i < tmp0.ptr.m_arr.length; ++i) {
            tmp0.ptr.m_arr[i] = DoubleValue.m_type.createDefault();
        }
        tmp0.ptr.m_ind = 0;
        tmp0.ptr.m_mem = tmp0.ptr.m_arr[0];
        tmp1.copyfrom(tmp0.ptr);
        ((DoubleValue) tmp1.m_mem).copyfrom(DoubleValue.create(1.0));
        tmp1.incIndex();
        ((DoubleValue) tmp1.m_mem).copyfrom(DoubleValue.create(2.0));
        tmp1.incIndex();
        ((DoubleValue) tmp1.m_mem).copyfrom(DoubleValue.create(3.0));
        tmp3.copyfrom(CCompArrayPtr.atspre_arrpsz_get_ptrsize(tmp0.deepcopy(), CCompTypedefs.CPtrValue.create(tmp2, SizeValue.m_type.getSize()).deepcopy()));
        CCompString.atspre_print_string(StringValue.create("A[0] = ").deepcopy());
        tmp6.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$arrayptr_056$sats__arrayptr_get_at_gint$0$1(tmp3.deepcopy(), IntValue.create(0).deepcopy()));
        CCompFloat.atspre_print_double(tmp6.deepcopy());
        CCompBasics.atspre_print_newline();
        CCompString.atspre_print_string(StringValue.create("A[1] = ").deepcopy());
        tmp28.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$arrayptr_056$sats__arrayptr_get_at_gint$0$2(tmp3.deepcopy(), IntValue.create(1).deepcopy()));
        CCompFloat.atspre_print_double(tmp28.deepcopy());
        CCompBasics.atspre_print_newline();
        CCompString.atspre_print_string(StringValue.create("A[2] = ").deepcopy());
        tmp41.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$arrayptr_056$sats__arrayptr_get_at_gint$0$3(tmp3.deepcopy(), IntValue.create(2).deepcopy()));
        CCompFloat.atspre_print_double(tmp41.deepcopy());
        CCompBasics.atspre_print_newline();
        return IntValue.create(0).deepcopy();
    }
    
    static public void main(String [] args) {
        mainats();
    }
    
}


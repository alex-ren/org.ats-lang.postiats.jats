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


public class fact_dats {
    static public IntValue fact_0(IntValue arg0) {
        IntValue tmp0 = IntValue.m_type.createDefault();
        BoolValue tmp1 = BoolValue.m_type.createDefault();
        IntValue tmp6 = IntValue.m_type.createDefault();
        IntValue tmp7 = IntValue.m_type.createDefault();
        IntValue tmp8 = IntValue.m_type.createDefault();
        IntValue tmp9 = IntValue.m_type.createDefault();
        tmp1.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1$1(arg0.deepcopy(), IntValue.create(0).deepcopy()));
        if (tmp1.isTrue()) {
            tmp7.copyfrom(CCompInteger.atspre_g1int_sub_int(arg0.deepcopy(), IntValue.create(1).deepcopy()));
            tmp6.copyfrom(fact_0(tmp7.deepcopy()));
            tmp8.copyfrom(tmp6);
            tmp9.copyfrom(CCompInteger.atspre_g1int_mul_int(arg0.deepcopy(), tmp8.deepcopy()));
            tmp0.copyfrom(tmp9);
        } else {
            tmp0.copyfrom(IntValue.create(1));
        }
        return tmp0.deepcopy();
    }

    static public BoolValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g1int_int$1$1(IntValue arg0, IntValue arg1) {
        BoolValue tmp2$1 = BoolValue.m_type.createDefault();
        IntValue tmp3$1 = IntValue.m_type.createDefault();
        tmp3$1.copyfrom(CCompInteger.atspre_g1int2int_int_int(arg1.deepcopy()));
        tmp2$1.copyfrom(CCompInteger.atspre_g1int_gt_int(arg0.deepcopy(), tmp3$1.deepcopy()));
        return tmp2$1.deepcopy();
    }

    static public IntValue mainats() {
        IntValue tmp10 = IntValue.m_type.createDefault();
        IntValue tmp15 = IntValue.m_type.createDefault();
        IntValue tmp16 = IntValue.m_type.createDefault();
        CCompString.atspre_print_string(StringValue.create("fact(").deepcopy());
        CCompInteger.atspre_print_int(IntValue.create(3).deepcopy());
        CCompString.atspre_print_string(StringValue.create(") = ").deepcopy());
        tmp16.copyfrom(fact_0(IntValue.create(3).deepcopy()));
        tmp15.copyfrom(tmp16);
        CCompInteger.atspre_print_int(tmp15.deepcopy());
        CCompBasics.atspre_print_newline();
        tmp10.copyfrom(IntValue.create(0));
        return tmp10.deepcopy();
    }
    
    static public void main(String [] args) {
        mainats();
    }
    
}


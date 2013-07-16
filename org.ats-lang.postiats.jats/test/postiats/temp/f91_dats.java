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


public class f91_dats {
    static public IntValue f91_0(IntValue arg0) {
        IntValue tmp0 = IntValue.m_type.createDefault();
        BoolValue tmp1 = BoolValue.m_type.createDefault();
        IntValue tmp6 = IntValue.m_type.createDefault();
        IntValue tmp7 = IntValue.m_type.createDefault();
        tmp1.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1$1(arg0.deepcopy(), IntValue.create(100).deepcopy()));
        if (tmp1.isTrue()) {
            tmp0.copyfrom(CCompInteger.atspre_g0int_sub_int(arg0.deepcopy(), IntValue.create(10).deepcopy()));
        } else {
            tmp7.copyfrom(CCompInteger.atspre_g0int_add_int(arg0.deepcopy(), IntValue.create(11).deepcopy()));
            tmp6.copyfrom(f91_0(tmp7.deepcopy()));
            tmp0.copyfrom(f91_0(tmp6.deepcopy()));
        }
        return tmp0.deepcopy();
    }

    static public BoolValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1$1(IntValue arg0, IntValue arg1) {
        BoolValue tmp2$1 = BoolValue.m_type.createDefault();
        IntValue tmp3$1 = IntValue.m_type.createDefault();
        tmp3$1.copyfrom(CCompInteger.atspre_g0int2int_int_int(arg1.deepcopy()));
        tmp2$1.copyfrom(CCompInteger.atspre_g0int_gt_int(arg0.deepcopy(), tmp3$1.deepcopy()));
        return tmp2$1.deepcopy();
    }

    static public IntValue mainats() {
        IntValue tmp8 = IntValue.m_type.createDefault();
        IntValue tmp11 = IntValue.m_type.createDefault();
        CCompString.atspre_print_string(StringValue.create("f91(10) = ").deepcopy());
        tmp11.copyfrom(f91_0(IntValue.create(10).deepcopy()));
        CCompInteger.atspre_print_int(tmp11.deepcopy());
        CCompBasics.atspre_print_newline();
        tmp8.copyfrom(IntValue.create(0));
        return tmp8.deepcopy();
    }
    
    static public void main(String [] args) {
        mainats();
    }
    
}


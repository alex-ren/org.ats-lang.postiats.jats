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


public class fib_dats {
    private static class postiats_tyrec_0 implements CCompCompositeValue {
        // line line 52

        // size info
        public static final int m_size = 8;

        // type info
        public static class m_type {
            public static int getSize() {
                return m_size;
            }

            public static postiats_tyrec_0 createDefault() {
                postiats_tyrec_0 obj = new postiats_tyrec_0();

                obj.atslab$0 = IntValue.m_type.createDefault();
                obj.atslab$1 = IntValue.m_type.createDefault();

                return obj;
            }
        }

        // member info
        public IntValue atslab$0;
        public IntValue atslab$1;

        // constructor
        private postiats_tyrec_0() {
        }

        @Override
        public void copyfrom(CCompCompositeValue v) {
            if (v instanceof postiats_tyrec_0) {
                postiats_tyrec_0 from = (postiats_tyrec_0) v;

                this.atslab$0.copyfrom(from.atslab$0);
                this.atslab$1.copyfrom(from.atslab$1);

            } else {
                throw new Error("Wrong type");
            }

        }

        @Override
        public postiats_tyrec_0 deepcopy() {
            postiats_tyrec_0 obj = new postiats_tyrec_0();

            obj.atslab$0 = atslab$0.deepcopy();
            obj.atslab$1 = atslab$1.deepcopy();

            return obj;
        }
    }

    static public IntValue fib_0(IntValue arg0) {
        IntValue tmp0 = IntValue.m_type.createDefault();
        postiats_tyrec_0 tmp11 = postiats_tyrec_0.m_type.createDefault();
        tmp11.atslab$0.copyfrom(IntValue.create(0));
        tmp11.atslab$1.copyfrom(IntValue.create(1));
        loop_1(CCompTypedefs.CPtrValue.create(tmp11, postiats_tyrec_0.m_type.getSize()).deepcopy(), arg0.deepcopy());
        tmp0.copyfrom((tmp11).atslab$0);
        return tmp0.deepcopy();
    }

    static public void loop_1(CCompTypedefs.CPtrValue arg0, IntValue arg1) {
        BoolValue tmp2 = BoolValue.m_type.createDefault();
        IntValue tmp7 = IntValue.m_type.createDefault();
        IntValue tmp8 = IntValue.m_type.createDefault();
        IntValue tmp9 = IntValue.m_type.createDefault();
        IntValue tmp10 = IntValue.m_type.createDefault();
        tmp2.copyfrom(_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2$1(arg1.deepcopy(), IntValue.create(0).deepcopy()));
        if (tmp2.isTrue()) {
            tmp7.copyfrom((((postiats_tyrec_0) arg0.deRef())).atslab$0);
            tmp8.copyfrom((((postiats_tyrec_0) arg0.deRef())).atslab$1);
            (((postiats_tyrec_0) arg0.deRef())).atslab$0.copyfrom(tmp8);
            tmp9.copyfrom(CCompInteger.atspre_g0int_add_int(tmp7.deepcopy(), tmp8.deepcopy()));
            (((postiats_tyrec_0) arg0.deRef())).atslab$1.copyfrom(tmp9);
            tmp10.copyfrom(CCompInteger.atspre_g0int_sub_int(arg1.deepcopy(), IntValue.create(1).deepcopy()));
            loop_1(arg0.deepcopy(), tmp10.deepcopy());
        } else {
        }
        return;
    }

    static public BoolValue _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2$1(IntValue arg0, IntValue arg1) {
        BoolValue tmp3$1 = BoolValue.m_type.createDefault();
        IntValue tmp4$1 = IntValue.m_type.createDefault();
        tmp4$1.copyfrom(CCompInteger.atspre_g0int2int_int_int(arg1.deepcopy()));
        tmp3$1.copyfrom(CCompInteger.atspre_g0int_gt_int(arg0.deepcopy(), tmp4$1.deepcopy()));
        return tmp3$1.deepcopy();
    }

    static public IntValue mainats() {
        IntValue tmp13 = IntValue.m_type.createDefault();
        IntValue tmp14 = IntValue.m_type.createDefault();
        tmp14.copyfrom(fib_0(IntValue.create(5).deepcopy()));
        CCompString.atspre_print_string(StringValue.create("ans = ").deepcopy());
        CCompInteger.atspre_print_int(tmp14.deepcopy());
        CCompBasics.atspre_print_newline();
        tmp13.copyfrom(IntValue.create(0));
        return tmp13.deepcopy();
    }
    
    static public void main(String [] args) {
        mainats();
    }
    
}


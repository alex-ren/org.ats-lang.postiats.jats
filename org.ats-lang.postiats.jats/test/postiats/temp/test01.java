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


public class test01 {
    static public IntValue dd = IntValue.m_type.createDefault();;

    static public IntValue mainats() {
        CCompString.atspre_print_string(StringValue.create("Hello World!").deepcopy());
        return IntValue.create(3).deepcopy();
    }
    
    static public void main(String [] args) {
        mainats();
    }
    
}


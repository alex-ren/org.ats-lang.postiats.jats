package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSCharType;
import jats.utfpl.stfpl.csharptype.CSPolyParaType;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Map;
import java.util.Set;

public class CharType extends EleType {
    public static CharType cInstance = new CharType();
    private CharType() {
        
    }
    @Override
    public ToCSTypeResult toCSType(Map<ICSTypeName, ICSType> map,
            Set<CSPolyParaType> esc) {
        return new ToCSTypeResult(CSCharType.c_instance, null);
    }

}

package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSFloatType;
import jats.utfpl.stfpl.csharptype.CSPolyParaType;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Map;
import java.util.Set;

public class FloatType extends EleType {
    public static FloatType cInstance = new FloatType();
    private FloatType() {
        
    }
    @Override
    public ToCSTypeResult toCSType(Map<ICSTypeName, ICSType> map,
            Set<CSPolyParaType> esc) {
        return new ToCSTypeResult(CSFloatType.c_instance, null);
    }

}

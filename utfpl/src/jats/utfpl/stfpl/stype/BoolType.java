package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSBoolType;
import jats.utfpl.stfpl.csharptype.CSPolyParaType;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Map;
import java.util.Set;

public class BoolType extends EleType {
    public static BoolType cInstance = new BoolType();
    private BoolType() {
        
    }
    
    @Override
    public ToCSTypeResult toCSType(Map<ICSTypeName, ICSType> map,
            Set<CSPolyParaType> esc) {
        return new ToCSTypeResult(CSBoolType.c_instance, null);
    }

}

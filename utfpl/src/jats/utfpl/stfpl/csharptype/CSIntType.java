package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSIntType extends CSEleType {
    public static CSIntType c_instance = new CSIntType();
    
    private CSIntType() {
        
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        ST st = stg.getInstanceOf("CSIntType_st");
        return st;
    }
}

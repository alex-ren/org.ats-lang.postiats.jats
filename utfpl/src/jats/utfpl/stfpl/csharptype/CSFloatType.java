package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSFloatType extends CSEleType {
    public static CSFloatType c_instance = new CSFloatType();
    
    private CSFloatType() {
        
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        ST st = stg.getInstanceOf("CSFloatType_st");
        return st;
    }

}

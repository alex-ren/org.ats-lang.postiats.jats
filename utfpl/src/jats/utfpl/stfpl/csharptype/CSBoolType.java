package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSBoolType extends CSEleType {
    public static CSBoolType c_instance = new CSBoolType();
    
    private CSBoolType() {
        
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        ST st = stg.getInstanceOf("CSBoolType_st");
        return st;
    }

}

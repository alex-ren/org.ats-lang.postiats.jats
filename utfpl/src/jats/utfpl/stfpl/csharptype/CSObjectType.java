package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSObjectType extends CSEleType {
    public static CSObjectType c_instance = new CSObjectType();
    
    private CSObjectType() {
        
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        ST st = stg.getInstanceOf("CSObjectType_st");
        return st;
    }


}

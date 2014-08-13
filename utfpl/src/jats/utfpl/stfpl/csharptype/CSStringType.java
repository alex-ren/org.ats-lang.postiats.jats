package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSStringType extends CSEleType {
    public static CSStringType c_instance = new CSStringType();
    
    private CSStringType() {
        
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        ST st = stg.getInstanceOf("CSStringType_st");
        return st;
    }

}

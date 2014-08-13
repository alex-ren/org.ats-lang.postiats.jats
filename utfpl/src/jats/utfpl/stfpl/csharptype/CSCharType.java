package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSCharType extends CSEleType {
    public static CSCharType c_instance = new CSCharType();
    
    private CSCharType() {
        
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        ST st = stg.getInstanceOf("CSCharType_st");
        return st;
    }


}

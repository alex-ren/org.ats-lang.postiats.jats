package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CSVoidType extends CSEleType {
    public static CSVoidType c_instance = new CSVoidType();
    
    private CSVoidType() {
        
    }

    @Override
    public ST toSt(STGroup stg, int level) {
        ST st = stg.getInstanceOf("CSVoidType_st");
        return st;
    }

}

package jats.utfpl.stfpl.csharptype;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public interface ICSType {
    
    public ST toSt(STGroup stg, int level);

}

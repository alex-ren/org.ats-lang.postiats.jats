package jats.utfpl.stfpl.csharpins;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public interface ICSInstruction {
    
    public ST toST(STGroup stg);

}

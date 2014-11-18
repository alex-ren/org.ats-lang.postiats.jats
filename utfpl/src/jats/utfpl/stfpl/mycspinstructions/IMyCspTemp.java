package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.stype.ISType;

public interface IMyCspTemp {
    public Object accept(IMyCspInsVisitor visitor);
    
    public ISType getType();
}

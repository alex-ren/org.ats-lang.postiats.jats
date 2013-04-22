package org.ats_lang.postiats.jats.type;

import java.util.Map;

public interface ATSUpdatableType {
    // create an updatable one and insert it into scope
    public ATSType createUpdatable(ATSType ty);
    
    public ATSType getInnerType();
    public void update(ATSType ty);

}

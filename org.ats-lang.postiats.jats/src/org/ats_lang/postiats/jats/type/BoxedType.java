package org.ats_lang.postiats.jats.type;

import java.util.Map;

public class BoxedType extends ATSUpdatableType {
    
    private BoxedType(ATSType ty) {
        super(ty);
    }
    
    @Override
    public int getSize() {
        return PtrkType.cType.getSize();
    }

    public static final BoxedType cType = new BoxedType(VoidType.cType);

    @Override
    public BoxedType createUpdatable(ATSType ty) {
        BoxedType ret = new BoxedType(ty);
        return ret;
    }

}

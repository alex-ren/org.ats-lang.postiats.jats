package org.ats_lang.postiats.jats.type;

import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.value.Ptrk;

public class BoxedType extends ATSKindType {  // ATSPrimType {  // ATSUpdatableType {

    public static final BoxedType cType = new BoxedType(VoidType.cType);
    
    private BoxedType(ATSType ty) {
//        super(ty);
        super(Decorator.TYPE);
    }
    
    @Override
    public int getSize() {
        return PtrkType.cType.getSize();
    }

    @Override
    public Map<String, Object> createNormalDefault() {
        return new HashMap<String, Object>();
    }

    @Override
    public Ptrk createRefDefault() {
        return new Ptrk(new HashMap<String, Object>());
    }
    
//    @Override
//    public BoxedType createUpdatable(ATSType ty) {
//        BoxedType ret = new BoxedType(ty);
//        return ret;
//    }

}

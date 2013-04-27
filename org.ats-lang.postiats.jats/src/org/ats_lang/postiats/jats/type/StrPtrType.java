package org.ats_lang.postiats.jats.type;

public class StrPtrType extends ATSUpdatableType {

    public static final StrPtrType cType = new StrPtrType(VoidType.cType);
    
    @Override
    public int getSize() {
        return PtrkType.cType.getSize();
    }

    private StrPtrType(ATSType ty) {
        super(ty);
    }
    
    @Override
    public StrPtrType createUpdatable(ATSType ty) {
    	StrPtrType ret = new StrPtrType(ty);
        return ret;
    }

}

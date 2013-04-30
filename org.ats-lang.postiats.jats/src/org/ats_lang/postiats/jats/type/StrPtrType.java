package org.ats_lang.postiats.jats.type;

public class StrPtrType extends ATSPrimType {

    public static final StrPtrType cType = new StrPtrType();
    
    @Override
    public int getSize() {
        return PtrkType.cType.getSize();
    }

    private StrPtrType() {
        super(Decorator.TYPE);
    }
    

}

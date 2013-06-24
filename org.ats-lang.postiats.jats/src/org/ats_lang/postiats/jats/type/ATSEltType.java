package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.SingletonValue;

/*
 * Elementary Type
 * Support basic types including int, char
 */
public abstract class ATSEltType extends ATSReferableType {
    

    public ATSEltType(Decorator dec) {
        super(dec);
    }
	
    @Override
    public SingletonValue createNormalDefault() {
        return SingletonValue.NULL;
    }

    
    
    @Override
    public boolean equals(ATSType ty) {
        return this == ty;
    }
    
    @Override    
    public Object cloneValue(Object src) {
    	return src;
    }

    
}

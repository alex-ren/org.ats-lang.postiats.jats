package org.ats_lang.postiats.jats.ccomp;


public interface CCompCompositeValue {

    public void copyfrom(CCompCompositeValue v);

    public CCompCompositeValue deepcopy();
    
}

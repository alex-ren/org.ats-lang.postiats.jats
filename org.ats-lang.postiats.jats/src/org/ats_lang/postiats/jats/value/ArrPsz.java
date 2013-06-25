package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ArrayType;


public class ArrPsz {
    private ArrayType m_arrType;
    private Ptrk m_ptr;

    public ArrPsz() {
        m_arrType = null;
        m_ptr = null;
    }
    
//    public void setSize(int asz) {
//        // no-op.
//        return;        
//    }
    
    public void init(int asz, ATSReferableType elety) {
        m_arrType = new ArrayType(elety, asz);
        Object [] arr = m_arrType.createNormalDefault();
        m_ptr = Ptrk.createPtrk(m_arrType, arr);
    }
    
    public int getAsz() {
        return m_arrType.getArraySize();
    }
    
    public Ptrk getPtr() {
        return m_ptr;
    }
    
//    public ATSReferableType getElementType() {
//        return m_elety;
//    }
}

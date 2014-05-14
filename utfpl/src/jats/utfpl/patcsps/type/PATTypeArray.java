package jats.utfpl.patcsps.type;

public class PATTypeArray implements PATType {
    
//    private PATType m_eleTy;
    private int m_size;
    
    public PATTypeArray(PATType eleTy, int sz) {
//        m_eleTy = eleTy;
        m_size = sz;
    }
    
    public PATTypeArray(int sz) {
//        m_eleTy = PATTypeSingleton.cUnknownType;
        m_size = sz;
    }
    
    public int getSize() {
        return m_size;
    }

}

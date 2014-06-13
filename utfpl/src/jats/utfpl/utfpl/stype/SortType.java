package jats.utfpl.utfpl.stype;

public abstract class SortType implements ISType {
    private ESort m_srt;
    
    public SortType(ESort srt) {
        m_srt = srt;
    }
    
    @Override
    public boolean equals(Object right) {
        throw new Error("should not use this");
        
    }
    
    

}

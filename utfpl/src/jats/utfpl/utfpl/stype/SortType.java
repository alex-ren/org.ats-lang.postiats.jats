package jats.utfpl.utfpl.stype;

public abstract class SortType implements ISType {
    private ESort m_srt;
    
    public SortType(ESort srt) {
        m_srt = srt;
    }

}

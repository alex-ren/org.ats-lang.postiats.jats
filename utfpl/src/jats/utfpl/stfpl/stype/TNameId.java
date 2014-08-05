package jats.utfpl.stfpl.stype;

public class TNameId implements ITypeName {
    private static int m_count = 0;
    
    private int m_stamp;
    private String m_symbol;
    
    static public TNameId createTypeId(String symbol) {
        int stamp = ++m_count;
        return new TNameId(stamp, symbol);
    }
    
    private TNameId(int stamp, String symbol) {
        m_stamp = stamp;
        m_symbol = symbol;
    }

}

package jats.utfpl.stfpl.csharptype;

public class CSTNameId implements ICSTypeName {
    private static int m_count = 0;
    
    private int m_stamp;
    private String m_symbol;
    
    static public CSTNameId createTypeId(String symbol) {
        int stamp = ++m_count;
        return new CSTNameId(stamp, symbol);
    }
    
    private CSTNameId(int stamp, String symbol) {
        m_stamp = stamp;
        m_symbol = symbol;
    }

    @Override
    public String toStringCS() {
        return m_symbol + m_stamp + "_id";
    }

}

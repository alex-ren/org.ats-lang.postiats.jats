package jats.utfpl.stfpl.csharptype;

public class CSTBookingFun implements ICSTypeBooking {
    public ICSTypeName m_name;
    public int m_para_size;
    
    public CSTBookingFun(ICSTypeName name, int para_size) {
        m_name = name;
        m_para_size = para_size;
    }

}

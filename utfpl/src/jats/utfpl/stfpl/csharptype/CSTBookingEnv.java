package jats.utfpl.stfpl.csharptype;

import jats.utfpl.stfpl.csharpins.CSSId;
import jats.utfpl.stfpl.csharpins.CSVar;

import java.util.Set;

public class CSTBookingEnv implements ICSTypeBooking {
    public CSSId m_name;  // name of the closure
    public Set<CSVar> m_env;
    
    public CSTBookingEnv(CSSId name, Set<CSVar> env) {
        m_name = name;
        m_env = env;
    }
}

package jats.utfpl.stfpl.csharptype;

import jats.utfpl.stfpl.csharpins.CSSId;
import jats.utfpl.stfpl.csharpins.CSSIdUser;

import java.util.Set;

public class CSTBookingEnv implements ICSTypeBooking {
    public CSSId m_name;  // name of the closure
    public Set<CSSIdUser> m_env;
    
    public CSTBookingEnv(CSSId name, Set<CSSIdUser> env) {
        m_name = name;
        m_env = env;
    }
}

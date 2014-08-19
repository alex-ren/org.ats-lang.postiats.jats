package jats.utfpl.stfpl.csharpins;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdUser;

public class CSSIdUser implements ICSValPrim {
    
    private SIdUser m_sid_user;
    private ICSType m_type;
    
    private static Map<SIdUser, CSSIdUser> s_map = new HashMap<SIdUser, CSSIdUser>();


    public static CSSIdUser fromSIdUser(SIdUser var, ICSType type) {
        CSSIdUser cs_sid_user = s_map.get(var);
        if (null != cs_sid_user) {
            return cs_sid_user;
        } else {
            CSSIdUser ret = new CSSIdUser(var, type);
            s_map.put(var, ret);
            return ret;
        }
    }

    public SId getSId() {
        return m_sid_user.getSId();
    }
    private CSSIdUser(SIdUser sid_user, ICSType type) {
        m_sid_user = sid_user;
        m_type = type;
    }
    
    @Override
    public ICSType getType() {
        return m_type;
    }

    @Override
    public String toStringCS() {
        return m_sid_user.toStringCS();
    }

}

package jats.utfpl.stfpl.mcinstruction;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.patcsps.Aux;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdUser;

public class MCSIdUser implements IMCIdPrim {
    
    private SIdUser m_sid_user;
    private MCSId m_mcsid;
    private ICSType m_type;
    
    private static Map<SIdUser, MCSIdUser> s_map = new HashMap<SIdUser, MCSIdUser>();


    public static MCSIdUser fromSIdUser(SIdUser var, ICSType type) {
        MCSIdUser cs_sid_user = s_map.get(var);
        if (null != cs_sid_user) {
            return cs_sid_user;
        } else {
        	MCSId mcsid = MCSId.fromSId(var.getSId(), type);
            MCSIdUser ret = new MCSIdUser(var, mcsid, type);
            s_map.put(var, ret);
            return ret;
        }
        
       
    }

    public void updateAddr(Aux.Address addr) {
    	m_mcsid.updateAddr(addr);
    }
    
    
    public SId getSId() {
        return m_sid_user.getSId();
    }
    
    private MCSIdUser(SIdUser sid_user, MCSId mcsid, ICSType type) {
        m_sid_user = sid_user;
        m_mcsid = mcsid;
        m_type = type;
        
    }
    
    @Override
    public ICSType getType() {
        return m_type;
    }

	public Boolean hasSideEffect() {
	    return m_mcsid.hasEffect();
    }

//    @Override
//    public String toStringCS() {
//        return m_sid_user.toStringCS();
//    }

}

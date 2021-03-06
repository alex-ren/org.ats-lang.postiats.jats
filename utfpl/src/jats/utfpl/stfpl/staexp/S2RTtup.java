package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.stype.ESort;

import java.util.List;

public class S2RTtup implements Is2rt {
    public List<Is2rt> m_s2ts;
    
    public S2RTtup(List<Is2rt> s2ts) {
        m_s2ts = s2ts;
    }
    
    @Override
    public ESort simplify() {
        return ESort.advance;
    }

    @Override
    public boolean isType() {
        return false;
    }

}

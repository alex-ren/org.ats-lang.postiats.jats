package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.List;

public class CBlock implements CGroup {
    private List<CInstruction> m_inslst;
    
    public CBlock() {
        m_inslst = new ArrayList<CInstruction>();
    }

    public void add(CInstruction ins) {
        m_inslst.add(ins);
    }
    
    public int size() {
        return m_inslst.size();
    }
}

package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.List;

public class CEventBlock implements CBlock {
    public List<CInstruction> m_inslst;
    
    public CEventBlock() {
        m_inslst = new ArrayList<CInstruction>();
    }

    public void add(CInstruction ins) {
        m_inslst.add(ins);
    }
    
    public int size() {
        return m_inslst.size();
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
}

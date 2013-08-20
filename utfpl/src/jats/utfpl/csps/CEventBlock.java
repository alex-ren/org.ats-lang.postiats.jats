package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.List;

public class CEventBlock extends CBlock {
    public List<CInstruction> m_inslst;
    
    public CEventBlock(int level) {
        super(level);
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

    @Override
    public int process(int offset) {
        for (CInstruction ins: m_inslst) {
            offset = ins.process(offset);
        }
        return offset;
    }
}

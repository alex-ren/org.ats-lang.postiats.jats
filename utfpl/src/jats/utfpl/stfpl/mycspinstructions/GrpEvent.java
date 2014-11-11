package jats.utfpl.stfpl.mycspinstructions;

import java.util.ArrayList;
import java.util.List;

public class GrpEvent extends MyCspGroup {
    public List<MyCspInstruction> m_inslst;
    
    public GrpEvent() {
        m_inslst = new ArrayList<MyCspInstruction>();
    }

    public void add(MyCspInstruction ins) {
        m_inslst.add(ins);
    }
    
    public int size() {
        return m_inslst.size();
    }
    
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        for (MyCspInstruction ins: m_inslst) {
            offset = ins.process(offset);
        }
        return offset;
    }
}

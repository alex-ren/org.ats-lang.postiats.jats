package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.ArrayList;
import java.util.List;

public class GrpEvent extends MyCspGroup {
    public List<MyCspInstruction> m_inslst;
    public MCSId m_funname;
    public int m_no;
    
    public GrpEvent(MCSId funname, int no) {

    	m_funname = funname;
    	m_no = no;
    	
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

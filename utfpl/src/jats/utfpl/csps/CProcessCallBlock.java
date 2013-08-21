package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.instruction.TID;

public class CProcessCallBlock extends CAdvancedBlock {
    public TID m_funlab;  // Don't support function pointer.
    public List<CTemp> m_args;
    public CTempID m_ret;
    
    public CProcessCallBlock(TID funlab, List<CTemp> args, CTempID ret, int level) {
        super(level);
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
    }
    
    public CProcessCallBlock(TID funlab, int level) {
        super(level);
        m_funlab = funlab;
        m_args = null;
        m_ret = null;
    }
    
    public void reset(List<CTemp> args, CTempID ret) {
        m_args = args;
        m_ret = ret;
        
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    int process(int offset) {
        offset = m_ret.processFirstOccurrence(offset);
        
        List<CTemp> args = new ArrayList<CTemp>();
        for (CTemp arg: m_args) {
            if (arg instanceof CTempID) {
                CTempID ctid = (CTempID)arg;
                CTempID newTID = ctid.createForUsage(this.getLevel());  // create a new CTempID
                args.add(newTID);
            } else {
                args.add(arg);
            }
        }
        m_args = args;
        
        return offset;
    }
}

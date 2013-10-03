package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeFunc;
import jats.utfpl.patcsps.type.PATTypeSingleton;

public class CProcessCallBlock extends CAdvancedBlock {
    public TID m_funlab;  // Don't support function pointer.
    public List<CTemp> m_args;
    public CTempID m_ret;  // The holder for the return value of the function call.
    
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
    public int process(int offset) {
        if (((PATTypeFunc)m_funlab.getType()).getRetType() == PATTypeSingleton.cVoidType) {
            offset = m_ret.processFirstOccurrenceProcCall(offset, true);
        } else {
            offset = m_ret.processFirstOccurrenceProcCall(offset, false);
        }
        
        for (CTemp arg: m_args) {
            if (arg instanceof CTempID) {
                if (!((CTempID)arg).isFunc()) {
                    ((CTempID)arg).updateForUsage(this.getLevel());  // create a new CTempID
                }
            } else {
            }
        }
        
        return offset;
    }
}

package jats.utfpl.csps;

import jats.utfpl.instruction.TID;

import java.util.ArrayList;
import java.util.List;

public class CIFunCall implements CInstruction {
    public TID m_funlab;  // Don't support function pointer.
    public List<CTemp> m_args;
    public CTempID m_ret;
    
    private CBlock m_blk;
    
    public CIFunCall(TID funlab, List<CTemp> args, CTempID ret, CBlock blk) {
        m_funlab = funlab;
        m_args = args;
        m_ret = ret;
        
        m_blk = blk;
        
    }
    
    public boolean isRet() {
        return m_ret.isRet();
    }
    
    public boolean needStack() {
        return m_ret.isEscaped();
    }
    

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this, m_blk);
    }

    @Override
    public CBlock getBlock() {
        return m_blk;
    }

    @Override
    public int process(int offset) {
        offset = m_ret.processFirstOccurrence(offset);
        
        List<CTemp> args = new ArrayList<CTemp>();
        for (CTemp arg: m_args) {
            if (arg instanceof CTempID) {
                CTempID ctid = (CTempID)arg;
                CTempID newTID = ctid.createForUsage(m_blk.getLevel());  // create a new CTempID
                args.add(newTID);
            } else {
                args.add(arg);
            }
        }
        m_args = args;
        
        return offset;
        
    }

}


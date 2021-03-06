package jats.utfpl.csps;

import java.util.List;

public class CICond extends CInstruction {
    public CTempID m_holder;  // This can be null, which means that those two branches
                              // are ended by some "return" instructions.
    public CTemp m_cond;
    public List<CInstruction> m_true;
    public List<CInstruction> m_false;

    public CICond(CTempID holder, CTemp cond, 
            List<CInstruction> trueBranch, List<CInstruction> falseBranch, 
            CBlock blk) {
        super(blk);
        m_holder = holder;
        m_cond = cond;
        m_true = trueBranch;
        m_false = falseBranch;
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        // This one should be done first so that
        // holder can be recognized as a definition.
    	if (null != m_holder) {
            offset = m_holder.processStack(offset);
    	}
        
        if (m_cond instanceof CTempID) {
            ((CTempID)m_cond).updateForUsage();
        }
        
        for (CInstruction ins: m_true) {
            ins.process(0);  // 0 is just a placeholder, 
                             // we can use any value, since nothing shall be put on stack
        }
        
        for (CInstruction ins: m_false) {
            ins.process(0);  // 0 is just a placeholder, 
                             // we can use any value, since nothing shall be put on stack
        }
        
        return offset;
    }

}

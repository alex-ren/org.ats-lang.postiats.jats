package jats.utfpl.stfpl.mycspinstructions;

import java.util.List;

public class CICond extends MyCspInstruction {
    public MyCspTempID m_holder;  // This can be null, which means that those two branches
                              // are ended by some "return" instructions.
    public IMyCspTemp m_cond;
    public List<MyCspInstruction> m_true;
    public List<MyCspInstruction> m_false;

    public CICond(MyCspTempID holder, IMyCspTemp cond, 
            List<MyCspInstruction> trueBranch, List<MyCspInstruction> falseBranch, 
            MyCspGroup blk) {
        super(blk);
        m_holder = holder;
        m_cond = cond;
        m_true = trueBranch;
        m_false = falseBranch;
    }
    
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        // No need to do this any more.
    	// There is an CIVarDef for the condition.
//    	if (null != m_holder) {
//            offset = m_holder.processStack(offset);
//    	}
        
        if (m_cond instanceof MyCspTempID) {
            ((MyCspTempID)m_cond).updateForUsage();
        }
        
        for (MyCspInstruction ins: m_true) {
            ins.process(0);  // 0 is just a placeholder, 
                             // we can use any value, since nothing shall be put on stack
        }
        
        for (MyCspInstruction ins: m_false) {
            ins.process(0);  // 0 is just a placeholder, 
                             // we can use any value, since nothing shall be put on stack
        }
        
        return offset;
    }

}

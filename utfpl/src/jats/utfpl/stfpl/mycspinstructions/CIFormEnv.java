package jats.utfpl.stfpl.mycspinstructions;


import java.util.Set;

public class CIFormEnv extends MyCspInstruction {
    public Set<MyCspTempID> m_eles;
    public MyCspTempID m_holder;
    
    public CIFormEnv(Set<MyCspTempID> eles, MyCspTempID holder, MyCspGroup blk) {
        super(blk);
        
        m_holder = holder;
        m_eles = eles;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);

        for (MyCspTempID arg: m_eles) {
        	arg.updateForUsage();
        }
        
        return offset;
    }

}

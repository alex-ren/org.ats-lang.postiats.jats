package jats.utfpl.stfpl.mycspinstructions;


import java.util.List;

public class CIFormEnv extends MyCspInstruction {
    public List<MyCspTempID> m_eles;
    public MyCspTempID m_holder;
    
    public CIFormEnv(List<MyCspTempID> eles, MyCspTempID holder, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        
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

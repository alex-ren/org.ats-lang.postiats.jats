package jats.utfpl.stfpl.mycspinstructions;


public class CIGetEleFromEnv extends MyCspInstruction {
    public String m_lab;
    public MyCspTempID m_holder;
    public MyCspTempID m_env;
    
    public CIGetEleFromEnv(MyCspTempID holder, MyCspTempID env, String lab, MyCspGroup blk) {
        super(blk);
        
        m_lab = lab;
        m_holder = holder;
        m_env = env;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        m_env.updateForUsage();
        
        return offset;
    }

}

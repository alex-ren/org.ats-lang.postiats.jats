package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.utils.Log;


public class CIGetEleFromEnv extends MyCspInstruction {
    public String m_lab;
    public int m_index;
    public MyCspTempID m_holder;
    public MyCspTempID m_env;
    
    public CIGetEleFromEnv(MyCspTempID holder, MyCspTempID env, String lab, int index, MyCspGroup blk) {
        super(blk);
        
        m_lab = lab;
        m_index = index;
        m_holder = holder;
        m_env = env;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
//    	Log.log4j.info("holder is " + m_holder.getMCSId().toStringMCIns() + " @ " + m_holder);

        offset = m_holder.processStack(offset);
        m_env.updateForUsage();
        
        return offset;
    }

}

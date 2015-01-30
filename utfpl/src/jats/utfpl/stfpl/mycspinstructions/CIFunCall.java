package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;

public class CIFunCall extends MyCspInstruction {
    public MCSId m_funlab;  // Currently, we assume this is function name.
    public List<IMyCspTemp> m_args;
//    public MyCspTempID m_envname;
    public MyCspTempID m_ret;
    public boolean m_isTail;  // useless currently

    
    public CIFunCall(MCSId funlab, List<IMyCspTemp> args, 
    		/* MyCspTempID envname, */
    		MyCspTempID ret, 
    		boolean isTail, 
    		MyCspGroup blk,
            boolean effect) {
        super(blk, effect);
        m_funlab = funlab;
        m_args = args;
//        m_envname = envname;
        m_ret = ret;
        m_isTail = isTail;

    }
    
    public boolean isRet() {
        return m_ret.isRet();
    }
    
    public boolean needStack() {
        return m_ret.isEscaped();
    }
    

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
//    	System.out.println("===== CIFunCall.process, funname is " + m_funlab.toStringMCIns() +
//    			  "ret is " + m_ret.getMCSId().toStringMCIns());
       	
        offset = m_ret.processStack(offset);
        for (IMyCspTemp arg: m_args) {
            if (arg instanceof MyCspTempID) {
                ((MyCspTempID)arg).updateForUsage();
            }
        }
        
        return offset;
        
    }

}


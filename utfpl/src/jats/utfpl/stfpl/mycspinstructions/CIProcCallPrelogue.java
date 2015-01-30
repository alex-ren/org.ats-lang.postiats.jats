package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;

public class CIProcCallPrelogue extends MyCspInstruction {
//	private MCSId m_fun;
	public List<IMyCspTemp> m_args;
//	public MyCspTempID m_envname;
	public boolean m_isTail;

    public CIProcCallPrelogue(MCSId fun, 
    		MyCspGroup blk, 
    		List<IMyCspTemp> args, 
    		/*MyCspTempID envname, */
    		boolean isTail) {
        super(blk, false);
//        m_fun = fun;
        m_args = args;
//        m_envname = envname;
        m_isTail = isTail;
    }

	@Override
    public Object accept(IMyCspInsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public int process(int offset) {
//        System.out.println("===== CIProcCallPrelogue.process, funname is " + m_fun.toStringMCIns());
        for (IMyCspTemp arg: m_args) {
            if (arg instanceof MyCspTempID) {
                if (!((MyCspTempID)arg).isFunName()) {
                    ((MyCspTempID)arg).updateForUsage();  // create a new CTempID
                }
            } else {
            }
        }
        return offset;
    }

}

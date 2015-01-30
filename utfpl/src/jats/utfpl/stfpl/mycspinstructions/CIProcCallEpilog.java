package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class CIProcCallEpilog extends MyCspInstruction {
	public MCSId m_funlab;  // Don't support function pointer.
    public MyCspTempID m_holder;  // The holder for the return value of the function call.
                           // m_ret.Anony() == false or
                           // m_ret.isRet() == false
    
    public CIProcCallEpilog(MyCspGroup blk, MCSId funlab, MyCspTempID holder) {
        super(blk, false);
    	m_funlab = funlab;
    	m_holder = holder;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
//       	if (m_holder.getMCSId().getSId().toStringWithStamp().equals("temp4_id")) {
//    		throw new Error("eeeeeeeeeeeee1");
//    	}
        offset = m_holder.processStackProcCallEpilogue(offset);
        return offset;        
    }

}

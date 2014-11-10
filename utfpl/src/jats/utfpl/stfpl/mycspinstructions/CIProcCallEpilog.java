package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeFunc;
import jats.utfpl.patcsps.type.PATTypeSingleton;

public class CIProcCallEpilog extends MyCspInstruction {
	public TID m_funlab;  // Don't support function pointer.
    public MyCspTempID m_holder;  // The holder for the return value of the function call.
                           // m_ret.Anony() == false or
                           // m_ret.isRet() == false
    
    public CIProcCallEpilog(MyCspGroup blk, TID funlab, MyCspTempID holder) {
    	super(blk);
    	m_funlab = funlab;
    	m_holder = holder;
    }

    @Override
    public Object accept(IMyCspVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        if (((PATTypeFunc)m_funlab.getType()).getRetType() == PATTypeSingleton.cVoidType) {
//        	System.out.println("type of " + m_funlab + " is void.");
            offset = m_holder.processStackProcCallEpilogue(offset, true);
        } else {
//        	System.out.println("type of " + m_funlab + " is " + ((PATTypeFunc)m_funlab.getType()).getRetType());
            offset = m_holder.processStackProcCallEpilogue(offset, false);
        }
        return offset;        
    }

}

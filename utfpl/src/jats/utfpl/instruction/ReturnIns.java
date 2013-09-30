package jats.utfpl.instruction;

import java.util.Map;

import jats.utfpl.instruction.TID;


/*
 * This instruction is added during the processing phase.
 */
public class ReturnIns implements UtfplInstruction {
	public TID m_tid;
	
	public ReturnIns(TID tid) {
	    if (!tid.isRet()) {
	        throw new Error("non-return value used in ReturnIns");
	    }
		m_tid = tid;
	}

	@Override
    public Object accept(InsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public boolean hasSideEffect() {
	    return false;
    }

//    public ReturnIns createSubs(Map<TID, TID> subMap) {
//        return new ReturnIns(
//                TID.subsTID(m_tid, subMap));
//    }
}



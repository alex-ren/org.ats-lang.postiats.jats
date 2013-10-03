package jats.utfpl.patcsps;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeFunc;
import jats.utfpl.patcsps.type.PATTypeSingleton;

public class Aux {
	static final public TID cSysTid;
	static final public PExpID cSysTidExp;
	
	static final public TID cSchedulerWTid;
	static final public TID cSysSch;
	static final public TID cSysSchStart;
	static final public TID cParaTid;
	static final public PExpID cArgTidExp;
	static final public PChannelSend cThreadHeader;
	static {
//		var<PStack> GStack = new PStack();
//		var SysTid = 0;
//		channel SysChSch 0;
//		channel SysChSchStart 0;
//		main1_s (tid, x) = SysChSchStart!tid -> 

		cSysTid      = TID.createGloVar("SysTid", true);
		cSysTidExp = new PExpID(cSysTid);
		
		cSchedulerWTid = TID.createLibFun(  // This type doesn't matter. We just need a TID.
		        "SchedulerW", 
		        new PATTypeFunc(PATTypeSingleton.cVoidType, true));
		
		cSysSch      = TID.createChannel("SysChSch", true);
		cSysSchStart = TID.createChannel("SysChSchStart", true);
		cParaTid     = TID.createPara("tid", true);
		cArgTidExp   = new PExpID(cParaTid);
		
		List<PExp> msgLst = new ArrayList<PExp>();
    	msgLst.add(cArgTidExp);
		cThreadHeader= new PChannelSend(cSysSchStart, msgLst);
	}
	
	public static class Address {
	    static private int m_allocator = 0;
	    private int m_i;
	    
	    static public Address createPointer() {
	        m_allocator++;
	        return new Address(m_allocator);
	    }
	    
	    private Address(int i) {
	        m_i = i;
	    }
	    
	    public int getValue() {
	        return m_i;
	    }
	    
	    @Override
	    public String toString() {
	        return Integer.toString(m_i);
	    }
	}

}

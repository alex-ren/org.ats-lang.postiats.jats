package jats.utfpl.patcsps;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.instruction.TID;

public class Aux {
	static final public TID cSysTid;
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

		cSysTid      = TID.createGloVar("SysTid");
		cSysSch      = TID.createChannel("SysSch");
		cSysSchStart = TID.createChannel("SysSchStart");
		cParaTid     = TID.createPara("tid");
		cArgTidExp   = new PExpID(cParaTid);
		
		List<PExp> msgLst = new ArrayList<PExp>();
    	msgLst.add(cArgTidExp);
		cThreadHeader= new PChannelSend(cSysSchStart, msgLst);
	}
	
	public static class Address {
	    static private int m_allocator = 0;
	    public int m_i;
	    
	    static public Address createPointer() {
	        m_allocator++;
	        return new Address(m_allocator);
	    }
	    
	    private Address(int i) {
	        m_i = i;
	    }
	}

}

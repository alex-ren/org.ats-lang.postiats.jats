package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class Aux {
	static final public TID cSysTid = TID.createGloVar("SysTid");
	static final public TID cSysSch = TID.createChannel("SysSch");
	static final public TID cSysSchStart = TID.createChannel("SysSchStart");
	static final public TID cParaTid = TID.createPara("tid");
	
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

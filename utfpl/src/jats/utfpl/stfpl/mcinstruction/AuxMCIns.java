package jats.utfpl.stfpl.mcinstruction;

public class AuxMCIns {
	
	public static class AddressAllocator {
		private int m_allocator;
		
		public AddressAllocator() {
			m_allocator = 0;
		}
		
		public Address createPointer() {
	        m_allocator++;
	        return new Address(m_allocator);
	    }
	}
	
	public static class Address {

	    private int m_i;

	    // used by allocator
	    public Address(int i) {
	        m_i = i;
	    }
	    
	    public int getValue() {
	        return m_i;
	    }

	    public String toStringMCIns() {
	        return Integer.toString(m_i);
	    }
	}
}

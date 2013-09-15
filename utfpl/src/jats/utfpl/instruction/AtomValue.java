package jats.utfpl.instruction;

public class AtomValue implements ValPrim {
    private String m_obj;
    
    public AtomValue(String obj) {
        m_obj = obj;
    }
    
    public String toString() {
        return m_obj;
    }
    
    public static AtomValue createFromInt(int x) {
    	return new AtomValue(Integer.toString(x));
    }
    
}

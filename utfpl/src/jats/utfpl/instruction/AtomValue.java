package jats.utfpl.instruction;

public class AtomValue implements ValPrim {
    private String m_obj;
    
    public AtomValue(String obj) {
        m_obj = obj;
    }
    
    @Override
    public String toString() {
        return m_obj;
    }

    public static AtomValue createFromInt(int x) {
    	return new AtomValue(Integer.toString(x));
    }
    
    public static AtomValue createFromBoolean(boolean x) {
        return new AtomValue(Boolean.toString(x));
    }
    
}

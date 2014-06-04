package jats.utfpl.utfpl.dynexp;

public enum Epckind {
    
    PCKcon("PCKcon"),       // 0 // nonlin
    PCKlincon("PCKlincon"), // 1 // lincon
    PCKfree("PCKfree"),     // 2 // freeing
    PCKunfold("PCKunfold"); // 3 // folding
 
    
    private String m_str;
    
    private Epckind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }
}

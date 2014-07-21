package jats.utfpl.stfpl.dynexp;

/*
 * val x = 1
 */
public enum Evalkind {
    VK_val("val"), 
    VK_val_pos("val+"), 
    VK_val_neg("val-"), 
    VK_prval("prval");
    
    private String m_str;
    
    private Evalkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

}



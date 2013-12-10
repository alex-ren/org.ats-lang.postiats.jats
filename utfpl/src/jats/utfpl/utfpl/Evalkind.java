package jats.utfpl.utfpl;

public enum Evalkind {
    VK_val("val"), 
    VK_val_pos("val+"), 
    VK_val_neg("val-"), 
    VK_ignored("ignore");
    
    private String m_str;
    
    private Evalkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

}



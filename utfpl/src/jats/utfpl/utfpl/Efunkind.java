package jats.utfpl.utfpl;

public enum Efunkind {
    FK_fn("fn"), // non-recursive function 
    FK_fnx("fnx"), // tail-recursive 
    FK_fun("fun"), // recursive
    Fk_ignored("ignore");
    
    private String m_str;
    
    private Efunkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

}


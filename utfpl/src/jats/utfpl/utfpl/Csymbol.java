package jats.utfpl.utfpl;

public class Csymbol {

    public String m_str;
    
    @Override
    public String toString() {
        return m_str;
    }
    
    public String getData() {
        return m_str;
    }
    
    public Csymbol(String str) {
        m_str = str;
    }
    
    public boolean equals(Csymbol right) {
        return m_str.equals(right.m_str);
    }

}

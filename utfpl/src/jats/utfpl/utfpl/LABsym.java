package jats.utfpl.utfpl;


public class LABsym implements Ilabel {
    public Csymbol m_sym;
    
    public LABsym(Csymbol sym) {
        m_sym = sym;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof LABsym) {
            LABsym right = (LABsym)o;
            return m_sym.equals(right.m_sym);
        } else {
            return false;
        }
    }

}

package jats.utfpl.utfpl.dynexp;

public enum Efunkind {
    FK_fn("fn"), // non-recursive function 
    FK_fnx("fnx"), // tail-recursive 
    FK_fun("fun"), // recursive
    
    FK_prfn("prfn"),
    FK_prfun("prfun"),
    
    FK_praxi("praxi"),
    FK_castfn("castfn");
    
    private String m_str;
    
    private Efunkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

}


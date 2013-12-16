package jats.utfpl.utfpl;

public class Cd2cst {
	// What is constant? True, False?
    public Cstamp m_stamp;
    public Csymbol m_symbol;
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_symbol;
    }
    
    public Cd2cst(Cstamp stamp, Csymbol symbol) {
        m_stamp = stamp;
        m_symbol = symbol;
    }

}

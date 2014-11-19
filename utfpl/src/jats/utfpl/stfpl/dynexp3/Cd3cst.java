package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.stype.ISType;

public class Cd3cst {

	// What is constant? True, False?
    // Some examples are function name (in Ci2mpdec)
    public Cstamp m_stamp;
    public Csymbol m_symbol;
    public ISType m_stype;
    
    // Used for print (stringtemplate)
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    // Used for print (stringtemplate)
    public Csymbol getSymbol() {
        return m_symbol;
    }
    
    // Used for print (stringtemplate)
    public ISType getType() {
        return m_stype;
    }
    
    public Cd3cst(Cstamp stamp, Csymbol symbol, ISType stype) {
        m_stamp = stamp;
        m_symbol = symbol;
        m_stype = stype;
    }
    
    public String toStringNoStamp() {
    	return m_symbol.toString();
    }
    
    @Override
    public String toString() {
    	// Constant has no use of symbol.
        return m_symbol.toString(); //  + "_" + m_stamp.getData();
    }
    
    public boolean compSymbolString(String x) {
        return m_symbol.equals(x);
    }

}

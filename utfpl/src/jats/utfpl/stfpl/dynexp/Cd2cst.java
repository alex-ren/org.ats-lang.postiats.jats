package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.staexp.Cs2exp;
import jats.utfpl.stfpl.stype.ISType;

public class Cd2cst {
	// What is constant? True, False?
    // Some examples are function name (in Ci2mpdec)
    public Cstamp m_stamp;
    public Cs2exp m_type;
    public Csymbol m_symbol;
    
    private ISType m_stype;
    
    // Used for print (stringtemplate)
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    // Used for print (stringtemplate)
    public Csymbol getSymbol() {
        return m_symbol;
    }
    
    // Used for print (stringtemplate)
    public Cs2exp getType() {
        return m_type;
    }
    
//    public void updateSType(ISType stype) {
//        if (null != m_stype) {
//            m_stype.match(stype);
//        } else {
//            m_stype = stype;
//        }
//    }
    
    public void normalizeType() {
        m_stype = m_stype.normalize();
//        Log.log4j.info(m_symbol + " has type" + m_stype.toSTStfpl3(AuxSType.cStg).render());
    }
    
    public ISType getSType() {
        return m_stype;
    }
    
    public Cd2cst(Cstamp stamp, Cs2exp type, Csymbol symbol, ISType stype) {
        m_stamp = stamp;
        m_type = type;
        m_symbol = symbol;
        if (null == stype) {
        	throw new Error("This is not allowed.");
        }
        m_stype = stype;
    }
    
    @Override
    public String toString() {
        return m_symbol.toString();
    }
    
    

}

package jats.utfpl.utfpl.dynexp3;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.staexp.Cs2exp;

/*
 * constructor for datatype
 */
public class Cd2con {
    public Cstamp m_stamp;
	public Cs2exp m_type;  // Caution: this can be null
	public Csymbol m_sym;
	

	public Cd2con(Cstamp stamp, Cs2exp type, Csymbol sym) {
            m_sym = sym;
            m_type = type;
            m_stamp = stamp;
        }

}



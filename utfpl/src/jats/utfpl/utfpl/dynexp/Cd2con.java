package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.staexp.Cs2exp;

public class Cd2con {
	public Csymbol m_sym;
	public Cs2exp m_type;
	public Cstamp m_stamp;

	public Cd2con(Csymbol sym, Cs2exp type, Cstamp stamp) {
            m_sym = sym;
            m_type = type;
            m_stamp = stamp;
        }

}



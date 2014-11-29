package jats.utfpl.stfpl.staexp;

public class FUNCLOclo implements Ifunclo {
    // pats_basics.sats
    // closure: knd=1/0/~1: ptr/clo/ref
    public int m_knd;
    
    public FUNCLOclo(int knd) {
        m_knd = knd;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FUNCLOclo)) {
            return false;
        }
        return m_knd == ((FUNCLOclo)o).m_knd;
    }

    @Override
    public boolean isClosure() {
        return true;
    }
    
    @Override
    public String toString() {
        switch (m_knd)
        {
        case 1:
            return "cloptr";
        case 0:
            return "cloclo";
        case -1:
            return "cloref";
        default:
            throw new Error(m_knd + " is not supported.");
        }
    }

	@Override
    public boolean match(Ifunclo right) {
	    if (null == right) {
	    	throw new Error("This should not happen.");
//	    	return true;
	    } else if (right instanceof FUNCLOclo) {
	    	return true;
	    } else if (right instanceof FunCloNA) {
	    	return false;
	    } else if (right instanceof FUNCLOfun) {
	    	return false;
	    } else {
	    	throw new Error("Ifunclo " + right + " is not supported.");
	    }
    }

}

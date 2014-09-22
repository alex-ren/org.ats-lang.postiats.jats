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

}

package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Cstamp;

public class TYRECKINDflt1 implements Ityreckind {
    public Cstamp m_stamp;
    
    public TYRECKINDflt1(Cstamp s) {
        m_stamp = s;
    }

    @Override
    public int getId() {
        return 0;  // 0: flat, 1: boxed
    }
}

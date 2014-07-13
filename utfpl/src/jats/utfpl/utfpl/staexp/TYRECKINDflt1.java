package jats.utfpl.utfpl.staexp;

import jats.utfpl.utfpl.Cstamp;

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

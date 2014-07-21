package jats.utfpl.stfpl.dynexp;


/*
 * Function implementation, value implementation.
 */
public class D2Cimpdec implements Id2ecl_node {
    public int m_knd;  // What is this for?
    public Ci2mpdec m_i2mpdec;
    
    public D2Cimpdec(int knd, Ci2mpdec i2mpdec) {
        m_knd = knd;
        m_i2mpdec = i2mpdec;
    }
}


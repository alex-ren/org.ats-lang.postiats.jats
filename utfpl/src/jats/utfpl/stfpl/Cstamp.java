package jats.utfpl.stfpl;

public class Cstamp {
    public int m_id;
    public int m_myid;
    
    public int getData() {
        return m_id;
    }
    
    public int getMydata() {
        return m_myid;
    }
    
    public Cstamp(int id) {
        m_id = id;
        m_myid = 0;
    }
    
    public Cstamp(int id, int myid) {
        m_id = id;
        m_myid = myid;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cstamp))
            return false;
        Cstamp pn = (Cstamp) o;
        return (pn.m_id == m_id && pn.m_myid == m_myid);
    }
    
    @Override 
    public int hashCode() {
        return m_id;
    }

}

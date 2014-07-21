package jats.utfpl.stfpl.dynexp3;



import java.util.List;

/*
 * Pattern of record.
 * List is in essence a record, with labels named by integer 0, 1, ...
 */
public class P3Trec implements Ip3at_node {

    public List<LABP3ATnorm> m_labpats;
    
    public int m_knd;  // 0: flat, 1: boxed
    
    public P3Trec(List<LABP3ATnorm> labpats, int knd) {
        m_labpats = labpats;
        m_knd = knd;
    }
}

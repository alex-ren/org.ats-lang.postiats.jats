package jats.utfpl.stfpl.dynexp;



import java.util.List;

/*
 * Pattern of record.
 * List is in essence a record, with labels named by integer 0, 1, ...
 */
public class P2Trec implements Ip2at_node {

    public List<Ilabp2at> m_labpats;
    public int m_npf;  // Number of proofs in the pattern, if it is >= 0.
                       // (proof always appears starting from beginning.)
    
    public int m_knd;  // 0: flat, 1: boxed
    
    public P2Trec(List<Ilabp2at> labpats, int npf, int knd) {
        m_labpats = labpats;
        m_npf = npf;
        m_knd = knd;
    }
}

package jats.utfpl.stfpl.dynexp;



import jats.utfpl.stfpl.LABint;

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

    @Override
    public void normalizeType() {
        for (Ilabp2at p2at: m_labpats) {
            p2at.normalizeType();
        }
    }
    
    public boolean isBoxed() {
        return 1 == m_knd;
    }

    public boolean isTuple() {
        if (m_labpats.isEmpty()) {
            return true;
        } else {
            if (((LABP2ATnorm)m_labpats.get(0)).m_lab instanceof LABint) {
                return true;
            } else {
                return false;
            }
        }
    }
}

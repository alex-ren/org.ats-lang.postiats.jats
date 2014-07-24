package jats.utfpl.stfpl.dynexp3;


import jats.utfpl.stfpl.stype.RecType;
import jats.utfpl.stfpl.stype.RecType.ILabPat;
import jats.utfpl.stfpl.stype.RecType.LabPatNorm;

import java.util.ArrayList;
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

    @Override
    public RecType getType() {
        List<ILabPat> lab_pats = new ArrayList<ILabPat>();
        for (LABP3ATnorm lab: m_labpats) {
            LabPatNorm lab_pat = new LabPatNorm(lab.m_lab, lab.m_pat.m_node.getType());
            lab_pats.add(lab_pat);
        }
        
        return new RecType(lab_pats, -1 /* no proof */, m_knd);
    }
}

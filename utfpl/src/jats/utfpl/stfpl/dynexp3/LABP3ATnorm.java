package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Ilabel;


/*
 * The object of this class represents an item in the record.
 */
public class LABP3ATnorm {
    public Ilabel m_lab;  // The label for the item.
                         // The label can be integer if the record is actually a list.
    public Cp3at m_pat;
    
    public LABP3ATnorm(Ilabel lab, Cp3at pat) {
        m_lab = lab;
        m_pat = pat;
    }

}

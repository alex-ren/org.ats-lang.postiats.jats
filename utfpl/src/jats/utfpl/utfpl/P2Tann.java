package jats.utfpl.utfpl;

public class P2Tann implements Ip2at_node {
    public Ip2at_node m_p2t;
    public Is2exp_node m_ann;
    
    public P2Tann(Ip2at_node p2t, Is2exp_node ann) {
        m_p2t = p2t;
        m_ann = ann;
    }

}

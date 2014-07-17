package jats.utfpl.utfpl.dynexp3;


public class D3Eifopt implements Id3exp_node {
    public Cd3exp m_test;
    public Cd3exp m_then;
    public Cd3exp m_else;  // may be null
    
    public D3Eifopt(Cd3exp _test, Cd3exp _then, Cd3exp _else) {
        m_test = _test;
        m_then = _then;
        m_else = _else;
    }
}

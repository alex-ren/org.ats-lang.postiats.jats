package jats.utfpl.utfpl.dynexp;


public class D2Eifopt implements Id2exp_node {
    public Cd2exp m_test;
    public Cd2exp m_then;
    public Cd2exp m_else;  // may be null
    
    public D2Eifopt(Cd2exp _test, Cd2exp _then, Cd2exp _else) {
        m_test = _test;
        m_then = _then;
        m_else = _else;
    }
}

package jats.utfpl.stfpl.staexp;


/*
 * The literal "4" in the following example is of S2Eintinf.
 * prfun qq2 {x:int} .<>.(x: propy (4)): int = 3
 */
public class S2Eintinf implements Is2exp_node {
    public String m_intinf;
    
    public S2Eintinf(String intinf) {
    	m_intinf = intinf;
    }

}

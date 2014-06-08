package jats.utfpl.utfpl.stype;

import jats.utfpl.utfpl.staexp.Cs2var;

import java.util.List;

public class UniType extends BoxedType {
    private List<Cs2var> m_vars;
    private ISType m_body;
    
    public UniType(List<Cs2var> vars, ISType body) {
        m_vars = vars;
        m_body = body;
    }

}

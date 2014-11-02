package jats.utfpl.stfpl.instructions;


import java.util.List;

public class DecFunGroup {
    public Edeckind m_knd;
    public List<SId> m_names;  // Declaration of all functions. Include
                               // extern fun foo (): void
                               // fun foo (): void = ()
                               // val foo = lam () => ()

    
    public DecFunGroup(Edeckind knd, List<SId> names) {
        m_knd = knd;
        m_names = names;

    }

}

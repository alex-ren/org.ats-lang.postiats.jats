package jats.utfpl.tree;

import java.util.List;

public class AppExp implements Exp {
    public Exp m_fun;
    public List<Exp> m_explst;
    
    public AppExp(Exp fun, List<Exp> explst) {
        m_fun = fun;
        m_explst = explst;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}

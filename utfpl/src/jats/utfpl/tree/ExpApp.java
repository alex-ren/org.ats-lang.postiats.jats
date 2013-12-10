package jats.utfpl.tree;

import java.util.List;

public class ExpApp implements IExp {
    public IExp m_fun;
    public List<IExp> m_explst;
    
    public ExpApp(IExp fun, List<IExp> explst) {
        m_fun = fun;
        m_explst = explst;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}

package jats.utfpl.tree;

import java.util.ArrayList;
import java.util.List;

public class TupleExp implements Exp {
    static public TupleExp Void = new TupleExp();
    
    List<Exp> m_components;
    
    private TupleExp() {
        m_components = null;
    }
    
    public TupleExp(Exp e, List<Exp> es) {
        m_components = new ArrayList<Exp>();
        m_components.add(e);
        m_components.addAll(es);
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}

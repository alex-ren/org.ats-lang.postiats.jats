package jats.utfpl.tree;

import java.util.ArrayList;
import java.util.List;

public class ExpTuple implements IExp {
    static public ExpTuple Void = new ExpTuple();  // empty tuple
    
    public List<IExp> m_components;
    
    private ExpTuple() {
        m_components = null;
    }
    
    public boolean isSingle() {
        if (m_components != null && m_components.size() == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public ExpTuple(IExp e, List<IExp> es) {
        m_components = new ArrayList<IExp>();
        m_components.add(e);
        m_components.addAll(es);
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}

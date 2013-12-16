package jats.utfpl.tree;

import java.util.ArrayList;
import java.util.List;

public class ExpTuple extends IExp {
    // static public ExpTuple Void = new ExpTuple(null);  // empty tuple
    
    public List<IExp> m_components;
    
    static public ExpTuple makeVoid(Location loc) {
    	return new ExpTuple(loc);
    }
    
    private ExpTuple(Location loc) {
        super(loc);
        m_components = null;
    }
    
    public boolean isVoid() {
        return m_components == null || m_components.size() == 0;
    }
    
    public boolean isSingle() {
        if (m_components != null && m_components.size() == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public ExpTuple(Location loc, IExp e, List<IExp> es) {
        super(loc);
        m_components = new ArrayList<IExp>();
        m_components.add(e);
        m_components.addAll(es);
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}

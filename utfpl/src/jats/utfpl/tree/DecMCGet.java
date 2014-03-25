package jats.utfpl.tree;

import java.util.List;

/*
 * prval (vals ...) = mc_get_int(ids ...)
 */
public class DecMCGet extends IDec {
    public List<ExpId> m_vals; 
    public List<ExpId> m_ids;
    
    public DecMCGet(Location loc, List<ExpId> vals, List<ExpId> ids) {
        super(loc);

        m_vals = vals;
        m_ids = ids;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}

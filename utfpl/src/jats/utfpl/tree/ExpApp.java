package jats.utfpl.tree;

import jats.utfpl.instruction.TID;

import java.util.List;

public class ExpApp extends IExp {
    public IExp m_fun;
    public List<IExp> m_explst;
    
    public ExpApp(Location loc, IExp fun, List<IExp> explst) {
        super(loc);
        if (!(fun instanceof ExpId)) {
        	throw new Error("Function has to be a name.");
        }
        m_fun = fun;
        m_explst = explst;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
    
    public ExpId getFunName() {
    	if (m_fun instanceof ExpId) {
    		return (ExpId)m_fun;
    	} else {
    		throw new Error("function name is not ExpId");
    	}
    }
}

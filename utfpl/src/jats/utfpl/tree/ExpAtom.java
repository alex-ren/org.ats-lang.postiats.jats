package jats.utfpl.tree;

import jats.utfpl.tree.type.TreeType;
import jats.utfpl.tree.type.TreeTypeBool;
import jats.utfpl.tree.type.TreeTypeInt;
import jats.utfpl.tree.type.TreeTypeString;

public class ExpAtom extends IExp {
    public String m_text;
    public TreeType m_type;
    
    private ExpAtom(Location loc, TreeType ty, String text) {
        super(loc);
        m_type = ty;
        m_text = text;
    }
    
    static public ExpAtom makeInt(Location loc, String text) {
    	return new ExpAtom(loc, TreeTypeInt.cType, text);
    }
    
    static public ExpAtom makeString(Location loc, String text) {
    	return new ExpAtom(loc, TreeTypeString.cType, text);
    }
    
    static public ExpAtom makeTrue(Location loc) {
    	return new ExpAtom(loc, TreeTypeBool.cType, "true");
    }
    
    static public ExpAtom makeFalse(Location loc) {
    	return new ExpAtom(loc, TreeTypeBool.cType, "false");
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}

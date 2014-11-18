package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.BoolType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.IntType;
import jats.utfpl.stfpl.stype.VoidType;

public class AtomValue implements IValPrim {
    private ISType m_ty;
    private String m_obj;
    
    public static AtomValue sEmpty = new AtomValue(VoidType.cInstance);

    private AtomValue(ISType ty) {
        m_ty = ty;
        m_obj = null;
    }

    public AtomValue(ISType ty, String obj) {
        m_ty = ty;
        m_obj = obj;
        if (null == obj) {
        	throw new Error("This is not allowed.");
        }
    }
    
    @Override
    public ISType getType() {
        return m_ty;
    }
    
    @Override
    public String toString() {
        if (null == m_obj) {
            return "AtomValue.None";
        } else {
            return m_obj;
        }

    }
    
    public static AtomValue createFromInt(int x) {
    	return new AtomValue(IntType.cInstance, Integer.toString(x));
    }
    
    public static AtomValue createFromBoolean(boolean x) {
        return new AtomValue(BoolType.cInstance, Boolean.toString(x));
    }

}

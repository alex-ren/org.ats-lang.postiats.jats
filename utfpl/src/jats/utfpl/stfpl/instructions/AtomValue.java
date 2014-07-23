package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.VoidType;

public class AtomValue implements IValPrim {
    private ISType m_ty;
    private String m_obj;
    
    public static AtomValue sEmpty = new AtomValue(VoidType.cInstance);

    private AtomValue(ISType ty) {
        m_ty = ty;
    }

    public AtomValue(ISType ty, String obj) {
        m_ty = ty;
        m_obj = obj;
    }
    
    @Override
    public ISType getSType() {
        return m_ty;
    }


}

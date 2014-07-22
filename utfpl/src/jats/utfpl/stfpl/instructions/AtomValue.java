package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.VoidType;

public class AtomValue implements IValPrim {
    private ISType m_ty;
    
    public static AtomValue sEmpty = new AtomValue(VoidType.cInstance);

    private AtomValue(ISType ty) {
        m_ty = ty;
    }

    @Override
    public ISType getSType() {
        return m_ty;
    }


}

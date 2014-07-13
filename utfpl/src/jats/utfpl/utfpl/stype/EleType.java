package jats.utfpl.utfpl.stype;

import java.util.Map;


public class EleType extends FlatType {
    public EleType() {
        super();
    }

    @Override
    public EleType normalize() {
        return this;
    }

    @Override
    public void match(ISType ty) {
        EleType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return;
        } else if (right0 instanceof EleType) {
            if (right0 != this) {
                throw new Error("Type mismatch.");
            }
        } else {
            throw new Error("Type mismatch. left is " + left + ", right is " + right0);
        }
        
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }
    
}



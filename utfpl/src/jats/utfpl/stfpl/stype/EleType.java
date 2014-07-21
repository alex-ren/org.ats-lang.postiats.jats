package jats.utfpl.stfpl.stype;

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
    public TypeCheckResult match(ISType ty) {
        EleType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof EleType) {
            if (right0 != this) {
                return new TypeCheckResult("Type mismatch 01. left is " + left + ", right is " + right0);
            } else {
                return new TypeCheckResult();
            }
        } else {
            return new TypeCheckResult("Type mismatch 01. left is " + left + ", right is " + right0);
        }
        
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }
    
}



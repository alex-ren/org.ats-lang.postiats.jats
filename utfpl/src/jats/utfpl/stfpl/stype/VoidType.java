package jats.utfpl.stfpl.stype;

import jats.utfpl.utils.Log;

import java.util.Map;

public class VoidType extends SortType {
    
    public VoidType() {
        super(ESort.t0ype);
    }

    public static VoidType cInstance = new VoidType();

    
    @Override
    public VoidType normalize() {
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(this);
            return new TypeCheckResult();
        } else if (this == right0) {
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
        }
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }

}

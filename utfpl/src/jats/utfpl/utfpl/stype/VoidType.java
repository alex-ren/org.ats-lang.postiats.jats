package jats.utfpl.utfpl.stype;

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
    public void match(ISType ty) {
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(this);
            return;
        } else if (this == right0) {
            
        } else {
            throw new Error("Type mismatch.");
        }
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }

}

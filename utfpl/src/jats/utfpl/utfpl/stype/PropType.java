package jats.utfpl.utfpl.stype;


public class PropType extends SortType {
    public static PropType cInstance = new PropType();

    private PropType() {
        super(ESort.prop);
    }

    @Override
    public PropType normalize() {
        return this;
    }

    @Override
    public ISType instantiate(PolyParaType para, ISType arg) {
        return this;
    }

    @Override
    public void match(ISType ty) {
        PropType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return;
        } else if (right0 instanceof PropType) {
            if (this != right0) {
                throw new Error("type mismatch");
            }
        } else {
            throw new Error("Type mismatch.");
        }
    }


}

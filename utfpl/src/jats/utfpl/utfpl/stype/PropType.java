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


}

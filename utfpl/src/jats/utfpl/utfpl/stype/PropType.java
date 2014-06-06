package jats.utfpl.utfpl.stype;


public class PropType extends SortType {
    public static PropType cInstance = new PropType();

    private PropType() {
        super(ESort.prop);
    }

//    @Override
//    public boolean equals(ISType ty) {
//        return ty == this;
//    }

}

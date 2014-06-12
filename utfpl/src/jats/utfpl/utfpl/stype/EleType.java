package jats.utfpl.utfpl.stype;

public class EleType extends FlatType {
    public EleType() {
        super();
    }

    @Override
    public EleType normalize() {
        return this;
    }

    @Override
    public ISType instantiate(PolyParaType para, ISType arg) {
        return this;
    }

//    @Override
//    public boolean equals(ISType ty) {
//        if (ty == this) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}



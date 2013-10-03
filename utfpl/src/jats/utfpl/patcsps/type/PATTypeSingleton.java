package jats.utfpl.patcsps.type;

public class PATTypeSingleton implements PATType {
    
    public static final PATTypeSingleton cVoidType = new PATTypeSingleton();
    public static final PATTypeSingleton cUnknownType = new PATTypeSingleton();
    
    private PATTypeSingleton() {
    }

}

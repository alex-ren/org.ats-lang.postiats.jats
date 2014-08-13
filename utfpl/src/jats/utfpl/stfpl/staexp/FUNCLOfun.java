package jats.utfpl.stfpl.staexp;

public class FUNCLOfun implements Ifunclo {
    public static FUNCLOfun cInstance = new FUNCLOfun();
    private FUNCLOfun() {}
    @Override
    public boolean isClosure() {
        return false;
    }

}

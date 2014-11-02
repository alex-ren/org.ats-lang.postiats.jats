package jats.utfpl.stfpl.staexp;

public class FunCloNA implements Ifunclo {
    
    public static FunCloNA cInstance = new FunCloNA();
    
    private FunCloNA() {}

    @Override
    public boolean isClosure() {
        return true;
    }
    
    @Override
    public String toString() {
        return "n/a";
    }

}

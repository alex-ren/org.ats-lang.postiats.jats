package jats.utfpl.stfpl.staexp;

public interface Ifunclo {
    public boolean isClosure();
    
    public boolean match(Ifunclo right);
    
    @Override
    public String toString();

}

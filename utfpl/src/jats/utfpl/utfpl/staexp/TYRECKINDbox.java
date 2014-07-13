package jats.utfpl.utfpl.staexp;

public class TYRECKINDbox implements Ityreckind {
    static public TYRECKINDbox cInstance = new TYRECKINDbox();
    
    private TYRECKINDbox() {
        
    }

    @Override
    public int getId() {
        return 1;  // 0: flat, 1: boxed
    }
        
}

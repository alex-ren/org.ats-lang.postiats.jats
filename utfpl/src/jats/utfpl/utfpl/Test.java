package jats.utfpl.utfpl;

public class Test {
    
    static interface Too {
        public <T> T get();
    }
    
    static class Foo implements Too {
        public <T> T get() {
            return (T)"str";
        }
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        Foo foo = new Foo();
        Integer x = foo.get();
        x = x - 1;

    }

}

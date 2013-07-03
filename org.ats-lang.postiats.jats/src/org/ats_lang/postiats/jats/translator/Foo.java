package org.ats_lang.postiats.jats.translator;

public class Foo {
    static public class Inner {
        int get(int x) {
            return x + 1;
        }
    }
    
    static public Inner m_o;
    static {
        m_o = new Inner();
    }
    static public int m = m_o.get(1);

    
    
    public static void main(String[] args) {
        System.out.println("m is " + m);
    }

}

package org.ats_lang.postiats.jats.interpreter;

//Test.java
public class TTemp {
    // A.java
    static public class A {
    	public void show() {
    		System.out.println(this);
    	}
    }

    // B.java
    static public class B extends A {
    	
    }

    public static void main(String[] args) {
        B[] b = new B[1];
        b[0] = new B();
        System.out.println(b instanceof Object[]);
        
        
        System.out.println("ddrefref11".matches(".*ref\\d+"));
        
        B objb = new B();
        objb.show();
    }
}
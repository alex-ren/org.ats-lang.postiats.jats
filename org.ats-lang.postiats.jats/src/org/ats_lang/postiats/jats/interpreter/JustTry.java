package org.ats_lang.postiats.jats.interpreter;

import java.util.HashMap;
import java.util.Map;

public class JustTry {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Object> xx = new HashMap<String, Object>();
        xx.put("a", 3);
        System.out.println("xx.hash is " + xx.hashCode());
        System.out.println("xx.hash2 is " + System.identityHashCode(xx));
        
        xx.put("a", 4);
        System.out.println("xx.hash is " + xx.hashCode());
        System.out.println("xx.hash2 is " + System.identityHashCode(xx));

    }

}

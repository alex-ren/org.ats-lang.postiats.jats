package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.List;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(0);
        lst.add(1);
        lst.add(2);
        
        List<Integer> lst2 = new ArrayList<Integer>();
        lst2.add(3);
        
        List<Integer> lst3 = lst.subList(1, 2);
        
        lst2.addAll(lst3);
        
        System.out.println("lst2 is " + lst2.size() + ", lst is " + lst.size());
        
        lst3.clear();
        
        System.out.println("lst2 is " + lst2.size() + ", lst is " + lst.size());
        
        return;

    }

}

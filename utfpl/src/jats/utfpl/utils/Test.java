package jats.utfpl.utils;

import java.util.HashMap;
import java.util.Map;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> mA = new HashMap<String, String>();
		mA.put("a", "am");
		Map<String, String> mB = new HashMap<String, String>(mA);
		mB.put("a", "nota");
		mB.put("b",	"bm");
		
		System.out.println("a is " + mA.get("a") + " in A");
		System.out.println("a is " + mB.get("a") + " in B");
		

	}

}

package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.VoidType;


public class D3Eempty implements Id3exp_node {
	public static D3Eempty cInstance = new D3Eempty();
	
	private D3Eempty() {
	}

    @Override 
    public ISType getType() {
        return VoidType.cInstance;
    }

}

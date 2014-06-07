package jats.utfpl.utfpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jats.utfpl.utfpl.dynexp.Cd2cst;
import jats.utfpl.utfpl.dynexp.Cd2ecl;
import jats.utfpl.utfpl.dynexp.D2Cdcstdecs;
import jats.utfpl.utfpl.dynexp.D2Cextcode;
import jats.utfpl.utfpl.dynexp.D2Cfundecs;
import jats.utfpl.utfpl.dynexp.D2Cignored;
import jats.utfpl.utfpl.dynexp.D2Cimpdec;
import jats.utfpl.utfpl.dynexp.D2Cstacsts;
import jats.utfpl.utfpl.dynexp.D2Cvaldecs;
import jats.utfpl.utfpl.dynexp.Id2ecl_node;
import jats.utfpl.utfpl.dynexp.ProgramUtfpl;
import jats.utfpl.utfpl.stype.ISType;


public class UtfplTypeChecker {
    private ProgramUtfpl m_prog;
    private Map<Cd2cst, ISType> m_tymap;

    public UtfplTypeChecker(ProgramUtfpl prog) {
        m_prog = prog;
    }
    
    public void typecheck() {
        List<Cd2ecl> decs = new ArrayList<Cd2ecl>();
        for (Cd2ecl dec: m_prog.m_d2ecs) {
            typecheck(dec);
        }
    }

    private void typecheck(Cd2ecl dec) {
    	Id2ecl_node d2ecl = dec.d2ecl_node;
    	if (d2ecl instanceof D2Cdcstdecs) {
    		typecheck((D2Cdcstdecs)d2ecl);
    	} else if (d2ecl instanceof D2Cextcode) {
    		typecheck((D2Cextcode)d2ecl);
    	} else if (d2ecl instanceof D2Cfundecs) {
    		typecheck((D2Cfundecs)d2ecl);
    	} else if (d2ecl instanceof D2Cignored) {
    		typecheck((D2Cignored)d2ecl);
    	} else if (d2ecl instanceof D2Cimpdec) {
    		typecheck((D2Cimpdec)d2ecl);
    	} else if (d2ecl instanceof D2Cstacsts) {
    		typecheck((D2Cstacsts)d2ecl);
    	} else if (d2ecl instanceof D2Cvaldecs) {
    		typecheck((D2Cvaldecs)d2ecl);    		
    	} else {
    		throw new Error(dec + " is not supported.");
    	}
    }

	private void typecheck(D2Cdcstdecs d2ecl) {
	    for (Cd2cst d2cst: d2ecl.m_d2cst) {
	    	typecheck(d2cst);
	    }
	    
    }

	private ISType typecheck(Cd2cst d2cst) {
	    // TODO Auto-generated method stub
	    
    }
    


}



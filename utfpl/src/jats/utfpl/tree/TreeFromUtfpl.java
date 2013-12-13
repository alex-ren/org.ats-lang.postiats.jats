package jats.utfpl.tree;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.utfpl.Cd2ecl;
import jats.utfpl.utfpl.Cd2var;
import jats.utfpl.utfpl.Cp2at;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.Cv2aldec;
import jats.utfpl.utfpl.D2Cfundecs;
import jats.utfpl.utfpl.D2Cignored;
import jats.utfpl.utfpl.D2Cimpdec;
import jats.utfpl.utfpl.D2Cvaldecs;
import jats.utfpl.utfpl.Id2ecl_node;
import jats.utfpl.utfpl.Ip2at_node;
import jats.utfpl.utfpl.P2Tany;
import jats.utfpl.utfpl.P2Tpat;
import jats.utfpl.utfpl.P2Tvar;
import jats.utfpl.utfpl.UtfplProgram;

public class TreeFromUtfpl {
	
	public ProgramTree trans(UtfplProgram uProg) {
		List<IDec> decs = new ArrayList<IDec>();
		for (Cd2ecl uD2ecl: uProg.m_d2ecs) {
			IDec dec = transGlobalCd2ecl(uD2ecl);
			decs.add(dec);
		}
		
		return new ProgramTree(decs);
	}

	private IDec transGlobalCd2ecl(Cd2ecl uD2ecl) {
		Id2ecl_node uNode = uD2ecl.d2ecl_node;
	    if (uNode instanceof D2Cvaldecs) {
	    	return transGlobalD2Cvaldecs((D2Cvaldecs)uNode);
	    } else if (uNode instanceof D2Cfundecs) {
	    	return transD2Cfundecs((D2Cfundecs)uNode);
	    } else if (uNode instanceof D2Cimpdec) {
	    	throw new Error("D2Cimpdec is not supported");
	    } else if (uNode instanceof D2Cignored) {
	    	// do nothing
	    } else {
	    	throw new Error(uNode + " is not supported.");
	    }
    }

	private DecValDef transGlobalD2Cvaldecs(D2Cvaldecs uNode) {
		List<Cv2aldec> uV2ds = uNode.m_v2ds;
		if (uV2ds.size() > 1) {
			throw new Error("Group definition of values is not supported.");
		}
		Cv2aldec uValdec = uV2ds.get(0);
		return transGlobalCv2aldec(uValdec);
		
    }

	private DecValDef transGlobalCv2aldec(Cv2aldec uNode) {
		ExpId id = transCp2at(uNode.v2aldec_pat);
		IExp def = transCd2exp(uNode.v2aldec_def);
		
		return new DecValDef(id, def);
    }

	private ExpId transCp2at(Cp2at uNode) {
		Ip2at_node uPat = uNode.p2at_node;
		if (uPat instanceof P2Tany) {
			return transP2Tany((P2Tany)uPat);
		} else if (uPat instanceof P2Tpat) {
			return transP2Tpat((P2Tpat)uPat);
		} else if (uPat instanceof P2Tvar) {
			return transP2Tvar((P2Tvar)uPat);
		} else {
			throw new Error(uNode + " is not supported");
		}
    }

	private ExpId transP2Tvar(P2Tvar uNode) {
	    return new ExpId(transCd2var(uNode.m_var));
    }

	private String transCd2var(Cd2var uNode) {
	    return transCsymbol(uNode.m_sym);
    }

	private String transCsymbol(Csymbol uNode) {
	    return uNode.m_str;
    }

	private ExpId transP2Tpat(P2Tpat uNode) {
	    return transCp2at(uNode.m_p2at);
    }

	private ExpId transP2Tany(P2Tany uNode) {
	    return new ExpId("_");
    }
	
	

}















package jats.utfpl.utfpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jats.utfpl.utfpl.dynexp.Cd2cst;
import jats.utfpl.utfpl.dynexp.Cd2ecl;
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

    private ISType typecheck(Cd2ecl dec) {
        // TODO Auto-generated method stub
        
    }
    


}



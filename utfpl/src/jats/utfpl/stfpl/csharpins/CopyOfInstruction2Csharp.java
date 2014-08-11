package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.dynexp.Cd2ecl;
import jats.utfpl.stfpl.dynexp.ProgramUtfpl;
import jats.utfpl.stfpl.dynexp3.Cd3ecl;

import java.net.URL;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class CopyOfInstruction2Csharp {

    private STGroup m_stg;
    
    private List<Cd3ecl> m_decs;
    
    public  CopyOfInstruction2Csharp(List<Cd3ecl> decs) {
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/instructions/csharp.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        m_decs = decs;

    }
    
    public String printCsharp() {
        ST st = printCd3ecl(m_decs);
        return st.render();
    }
    
    private ST printCd3ecl(List<Cd3ecl> m_decs2) {
        // TODO Auto-generated method stub
        return null;
    }

    private ST printUtfplProgram(ProgramUtfpl uProg) {
        // utfpl_prog_st(d2ecs) ::= <<
        ST st = m_stg.getInstanceOf("utfpl_prog_st");
        for (Cd2ecl d2ec: uProg.m_d2ecs) {
            st.add("d2ecs", printCd2ecl(d2ec));
        }
        return st;
    }
    
}

package jats.utfpl.patcsps;

import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import jats.utfpl.csps.CBlock;
import jats.utfpl.csps.CCondBlock;
import jats.utfpl.csps.CEventBlock;
import jats.utfpl.csps.CIFunCall;
import jats.utfpl.csps.CIMove;
import jats.utfpl.csps.CIProcessDef;
import jats.utfpl.csps.CProcessCallBlock;
import jats.utfpl.csps.CSPSVisitor;
import jats.utfpl.csps.CTempID;
import jats.utfpl.csps.CTempVal;
import jats.utfpl.csps.ProgramCSPS;
import jats.utfpl.instruction.TID;

public class PatCspsTransformer implements CSPSVisitor {

    @Override
    public Object visit(CCondBlock blk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CEventBlock blk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CProcessCallBlock blk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CIMove ins, CBlock curBlk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CIFunCall ins, CBlock curBlk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CIProcessDef proc, CBlock curBlk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CTempID v, CBlock curBlk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CTempVal v, CBlock curBlk) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(ProgramCSPS prog) {
        List<PGlobalVarDec> gvlst = new ArrayList<PGlobalVarDec>();
        for (TID gv: prog.m_globalVars) {
            PGlobalVarDec pgv = new PGlobalVarDec(gv);
            gvlst.add(pgv);            
        }
        
        
//        // program_st(gvarlst, proclst, mainproc, mainlab) ::= <<
//        ST st = m_stg.getInstanceOf("program_st");
//        for (TID gv: prog.m_globalVars) {
//            st.add("gvarlst", gv);
//        }
//        
//        for (CIProcessDef proc: prog.m_procLst) {
//            st.add("proclst", proc.accept(this));
//        }
//        
//        st.add("mainproc", printMain(prog.m_main));
//        st.add("mainlab", "main");
//        
//        return st;
    }

}









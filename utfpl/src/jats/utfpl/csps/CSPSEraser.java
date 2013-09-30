package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CSPSEraser implements CSPSVisitor {
    private List<CBlock> m_body;

    public CSPSEraser(List<CBlock> body) {
        m_body = body;
    }
    
    // Erase the CIProcessDef in CBlock.
    public void erase() {
        ListIterator<CBlock> iter = m_body.listIterator();
        while (iter.hasNext()) {
            CBlock blk = iter.next();
            Object ret = blk.accept(this);
            if (null == ret) {
                iter.remove();
            }
        }
    }

    @Override
    public Object visit(CCondBlock blk) {
        CSPSEraser eraserTrue = new CSPSEraser(blk.m_tb);
        eraserTrue.erase();
        
        CSPSEraser eraserFalse = new CSPSEraser(blk.m_fb);
        eraserFalse.erase();
        return blk;
    }

    @Override
    public Object visit(CEventBlock blk) {
        List<CInstruction> insLst = new ArrayList<CInstruction>();
        for (CInstruction ins: blk.m_inslst) {
            if (ins instanceof CIProcessDef) {
                // handle all the inner functions.
                ((CIProcessDef)ins).accept(this);
            } else {
                insLst.add(ins);
            }
        }
        blk.m_inslst = insLst;
        if (insLst.size() == 0) {
            return null;
        } else {
            return blk;
        }
    }

    @Override
    public Object visit(CProcessCallBlock blk) {
        return blk ;
    }

    @Override
    public Object visit(CIMove ins) {
        throw new Error("Should not happen");
    }

    @Override
    public Object visit(CIFunCall ins) {
        throw new Error("Should not happen");
    }

    @Override
    public Object visit(CIProcessDef proc) {
        CSPSEraser eraser = new CSPSEraser(proc.m_body);
        eraser.erase();
        return proc;
    }

    @Override
    public Object visit(CTempID v) {
        throw new Error("Should not happen");
    }

    @Override
    public Object visit(CTempVal v) {
        throw new Error("Should not happen");
    }

    @Override
    public Object visit(ProgramCSPS prog) {
        throw new Error("not support");
    }

    @Override
    public Object visit(CIReturn node) {
        throw new Error("Should not happen");
    }

}

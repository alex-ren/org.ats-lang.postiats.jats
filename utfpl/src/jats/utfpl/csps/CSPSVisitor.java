package jats.utfpl.csps;

public interface CSPSVisitor {
    public Object visit(CCondBlock blk);
    public Object visit(CEventBlock blk);
    public Object visit(CProcessCallBlock blk);
    
    
    public Object visit(CIMove ins, CBlock curBlk);
    public Object visit(CIFunCall ins, CBlock curBlk);
    public Object visit(CIProcessDef proc, CBlock curBlk);
    
    public Object visit(CTempID v, CBlock curBlk);
    public Object visit(CTempVal v, CBlock curBlk);

    public Object visit(ProgramCSPS prog);
}

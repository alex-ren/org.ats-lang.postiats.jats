package jats.utfpl.csps;

public interface CSPSVisitor {
    public Object visit(CCondBlock blk);
    public Object visit(CEventBlock blk);
    public Object visit(CProcessCallBlock blk);
    
    
    public Object visit(CIMove ins);
    public Object visit(CIFunCall ins);
    
    public Object visit(CTempID v);
    public Object visit(CTempVal v);
    

}

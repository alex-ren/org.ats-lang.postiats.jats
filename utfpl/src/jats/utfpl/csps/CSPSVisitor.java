package jats.utfpl.csps;

public interface CSPSVisitor {
    public Object visit(CCondBlock node);
    public Object visit(CEventBlock node);
    public Object visit(CProcessCallBlock node);
    
    
    public Object visit(CIMove node);
    public Object visit(CIFunCall node);
    public Object visit(CIProcessDef node);
    
    public Object visit(CTempID node);
    public Object visit(CTempVal node);

    public Object visit(ProgramCSPS node);
    
    public Object visit(CIReturn node);

}

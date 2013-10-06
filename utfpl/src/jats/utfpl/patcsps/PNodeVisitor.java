package jats.utfpl.patcsps;

public interface PNodeVisitor {
    public Object visit(PGDecVar node);
    
    public Object visit(PProcBranch node);

    public Object visit(PEvent node);
    
    public Object visit(PExpFuncCall node);
    public Object visit(PStatLocalVarDec node);
    public Object visit(PStatAssignment node);

    public Object visit(PExpAtom node);
    
    public Object visit(PProcAtom node);
    public Object visit(PProcCall node);
    public Object visit(PExpID node);
    
    public Object visit(PModel node);
    public Object visit(PGDecProc node);
    
    
    public Object visit(PProcSeq node);
    public Object visit(PExpStackOpr node);
    
    public Object visit(PProcEvent node);
    
    public Object visit(PInclude node);
    public Object visit(PGDecChan node);
    
    public Object visit(PProcChannel node);
    public Object visit(PChannelRecv node);
    public Object visit(PChannelSend node);
    public Object visit(PExpOpr node);
    public Object visit(PProcParallel node);
    
    public Object visit(PExpStackPush node);
    public Object visit(PStatReturn node);
    
    public Object visit(PExpTuple node);
    
}
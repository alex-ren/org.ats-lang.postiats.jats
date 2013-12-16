package jats.utfpl.csps;

public interface CSPSVisitor {
    
    public Object visit(CBCond node);
    public Object visit(CBEvent node);
    public Object visit(CBProc node);
    
    public Object visit(CIMove node);
    public Object visit(CIVarDef node);
    public Object visit(CIFunCall node);
    public Object visit(CIReturn node);
    public Object visit(CILoad node);
    public Object visit(CILoadArray node);
    public Object visit(CIMutexAlloc node);
    public Object visit(CIStore node);
    public Object visit(CIStoreArray node);
    public Object visit(CICond node);
    public Object visit(CIProcCallPrelogue node);
    public Object visit(CIProcCallEpilog node);
    
    public Object visit(CTempID node);
    public Object visit(CTempVal node);
    
}



package jats.utfpl.stfpl.mycspinstructions;

public interface IMyCspVisitor {
    
    public Object visit(GrpCond node);
    public Object visit(GrpEvent node);
    public Object visit(GrpProc node);
    
    public Object visit(CIMove node);
    public Object visit(CIVarDef node);
    public Object visit(CIFunCall node);
    public Object visit(CIReturn node);
    public Object visit(CILoad node);
    public Object visit(CILoadArray node);
    
    public Object visit(CIMutexAlloc node);
    public Object visit(CIMutexRelease node);
    
    public Object visit(CICondAlloc node);
    public Object visit(CICondRelease node);
    
    public Object visit(CIStore node);
    public Object visit(CIStoreArray node);
    public Object visit(CICond node);
    public Object visit(CIProcCallPrelogue node);
    public Object visit(CIProcCallEpilog node);
    
    public Object visit(MyCspTempID node);
    public Object visit(MyCspTempVal node);
    
    public Object visit(GrpThreadCreate node);
    public Object visit(CIMCAssert cimcAssert);
}



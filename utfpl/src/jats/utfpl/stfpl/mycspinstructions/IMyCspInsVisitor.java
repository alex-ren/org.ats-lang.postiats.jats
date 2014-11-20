package jats.utfpl.stfpl.mycspinstructions;

public interface IMyCspInsVisitor {
    
    public Object visit(MyCspTempID node);
    public Object visit(MyCspTempVal node);
    
    
    public Object visit(GrpCond node);
    public Object visit(GrpEvent node);
    public Object visit(GrpProc node);

    
    public Object visit(CIMove node);
    public Object visit(CIVarDef node);
    public Object visit(CIFunCall node);
    public Object visit(CIReturn node);

    public Object visit(CICond node);
    public Object visit(CIProcCallPrelogue node);
    public Object visit(CIProcCallEpilog node);
    
    public Object visit(CIFormTuple node);
    public Object visit(CIFormEnv node);
    public Object visit(CIPatLabDecompose node);
	public Object visit(CIGetEleFromEnv node);
	public Object visit(CIFormClosure node);
	
	
    public Object visit(CIMutexCreate node);
    
    public Object visit(CISharedCreateCond node);
    
    public Object visit(GrpThreadCreate node);
    
    public Object visit(CIAtomRefUpdate node);
	public Object visit(CIAtomRefCreate node);
    public Object visit(CIAtomRefGet node);
    
	public Object visit(CIArrayRefUpdate node);
	public Object visit(CIArrayRefCreate node);
	public Object visit(CIArrayRefGet node);
	

    public Object visit(CIMCAssert node);
    public Object visit(CIMCGet node);
    public Object visit(CIMCSet node);
    
	public Object visit(GrpMCAtomicStart node);

	public Object visit(CIMCVLockViewGet node);


    
    
    
}

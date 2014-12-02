package jats.utfpl.stfpl.pats;

public interface PNodeVisitor {
	

	public Object visit(PModel node);
    
    public Object visit(PGDecVar node);
    public Object visit(PGDecProc node);
    public Object visit(PGDecChan node);
//    public Object visit(PGDecArray node);
    
    public Object visit(PProcAtom node);
    public Object visit(PProcCall node);
    public Object visit(PProcBranch node);
    public Object visit(PProcSeq node);
    public Object visit(PProcEvent node);
    public Object visit(PProcChannel node);
    public Object visit(PProcParallel node);
	public Object visit(PProcGrpMCAtomicStart node);
	public Object visit(PProcGrpMCAtomicEnd node);
    
    public Object visit(PProcThreadCreate node);
    
    public Object visit(PNodeEvent node);
    
	public Object visit(PNodeMCAtomicStart node);
	public Object visit(PNodeMCAtomicEnd node);
    
    public Object visit(PInclude node);
    
    public Object visit(PChannelRecv node);
    public Object visit(PChannelSend node);
   
    
    public Object visit(PStatLocalVarDec node);
    public Object visit(PStatAssignment node);
    public Object visit(PStatReturn node);
    public Object visit(PStatProcCallPrelogue node);
    public Object visit(PStatProcCallEpilogue node);
    
    public Object visit(PExpID node);
    public Object visit(PExpFuncCall node);
    public Object visit(PExpAtom node);
    public Object visit(PExpStackGet node);
    public Object visit(PStatStackPush node);
//    public Object visit(PExpOpr node);
	public Object visit(PExpPatLabDecompose node);
	public Object visit(PExpFormClosure node);
    public Object visit(PExpTupleCreate node);
	public Object visit(PInsTupleAdd node);
	
    
    public Object visit(PInsCond node);
    
    public Object visit(PInsMutexCreate node);
//    public Object visit(PInsMutexRelease node);

//    public Object visit(PInsCondRelease node);



	
	public Object visit(PInsMCGet node);
	public Object visit(PInsMCSet node);
    public Object visit(PInsMCAssert node);
	public Object visit(PInsMCVLockViewGet node);
	public Object visit(PInsMCVLockViewPut node);
	
	
	public Object visit(PInsAtomRefCreate node);
    public Object visit(PInsAtomRefGet node);
    public Object visit(PInsAtomRefUpdate node);

	public Object visit(PInsArrayRefUpdate node);
	public Object visit(PInsArrayRefCreate node);
	public Object visit(PInsArrayRefGet node);

	public Object visit(PInsTIdAllocate node);

	public Object visit(PInsCondCreate node);





    

    
}

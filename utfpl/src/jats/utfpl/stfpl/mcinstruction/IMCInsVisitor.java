package jats.utfpl.stfpl.mcinstruction;

public interface IMCInsVisitor {
    
    public Object visit(MCInsTuple ins);
    
    public Object visit(MCInsPatLabDecompose ins);
    
    public Object visit(MCInsCond ins);

    public Object visit(MCInsMove ins);

    public Object visit(MCInsCall ins);

    /* ********* *********** */
    
    public Object visit(MCInsFormEnv ins);

    public Object visit(MCInsGetEleFromEnv ins);
    
    public Object visit(MCInsClosure ins);    

    /* ********* *********** */
    
    public Object visit(MCInsThreadCreate ins);


	public Object visit(MCInsMutexCreate ins);

    /* ********* *********** */
    
    public Object visit(MCInsMCAtomicStart ins);

    public Object visit(MCInsMCAssert ins);

    public Object visit(MCInsMCGet ins);

    public Object visit(MCInsMCSet ins);

    public Object visit(MCInsMCVLockViewGet ins);

	public Object visit(MCInsMCVLockViewPut ins);

    /* ********* *********** */
    
    public Object visit(MCInsAtomRefCreate ins);
    
    public Object visit(MCInsAtomRefGet ins);

    public Object visit(MCInsAtomRefUpdate ins);
    
	public Object visit(MCInsArrayRefCreate ins);

	public Object visit(MCInsArrayRefUpdate ins);

	public Object visit(MCInsArrayRefGet ins);

	public Object visit(MCInsTIdAllocate ins);

	public Object visit(MCInsSharedCreate ins);

	public Object visit(MCInsMCAtomicEnd ins);



    
    

}

package jats.utfpl.stfpl.mcinstruction;

public interface IMCInsVisitor {
    public Object visit(MCInsFormEnv ins);
    
    public Object visit(MCInsTuple ins);
    
    public Object visit(MCInsPatLabDecompose ins);
    
    public Object visit(MCInsCond ins);
    
    public Object visit(MCInsClosure ins);
    
    public Object visit(MCInsMove ins);

    public Object visit(MCInsCall ins);
    
    
    public Object visit(MCInsStore ins);
    
    public Object visit(MCInsLoad ins);
        
//    public Object visit(MCInsLoadArray ins);
    
    public Object visit(MCInsMutexAlloc ins);
    
    public Object visit(MCInsMutexRelease ins);
    
    public Object visit(MCInsCondAlloc ins);
    
    public Object visit(MCInsCondRelease ins);
    

    
    public Object visit(MCInsThreadCreate ins);
    
    public Object visit(MCInsMCAssert ins);

    public Object visit(MCInsMCGet insMCGet);

    public Object visit(MCInsMCSet insMCSet);

	public Object visit(MCInsGetEleFromEnv mcInsGetEleFromEnv);

}

package jats.utfpl.instruction;

public interface InsVisitor {
//    
//    public Object visit(GlobalArray ins);
//    
//    public Object visit(GlobalValue ins);
//    
//    public Object visit(GlobalVariable ins);
//    
    
    public Object visit(InsCond ins);
    
    public Object visit(InsCall ins);

    public Object visit(InsFuncDef ins);
    
    public Object visit(InsFuncGroup ins);
    
    public Object visit(InsStoreArray ins);
    
    public Object visit(InsStore ins);
    
    public Object visit(InsRet ins);
    
    public Object visit(InsLoadArray ins);
    
    public Object visit(InsMutexAlloc ins);
    
    public Object visit(InsMutexRelease ins);
    
    public Object visit(InsCondAlloc ins);
    
    public Object visit(InsCondRelease ins);
    
    public Object visit(InsMove ins);
    
    public Object visit(InsLoad ins);
    
    public Object visit(InsThreadCreate ins);
    
    public Object visit(InsMCAssert ins);

    public Object visit(InsMCGet insMCGet);

    public Object visit(InsMCSet insMCSet);

}

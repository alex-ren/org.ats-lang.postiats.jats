package jats.utfpl.instruction;

public interface InsVisitor {
    
    public Object visit(GlobalArray ins);
    
    public Object visit(GlobalValue ins);
    
    public Object visit(GlobalVariable ins);
    
    
    public Object visit(InsCond ins);
    
    public Object visit(InsCall ins);

    public Object visit(InsFuncDef ins);
    
    public Object visit(InsFuncGroup ins);
    
    public Object visit(InsStoreArray ins);
    
    public Object visit(InsStore ins);
    
    public Object visit(InsRet ins);
    
    public Object visit(InsLoadArray ins);
    
    public Object visit(InsAllocMutex ins);
    
    public Object visit(InsMove ins);
    
    public Object visit(InsLoad ins);

}

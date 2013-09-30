package jats.utfpl.instruction;

public interface InsVisitor {
    public Object visit(CondIns ins);
    
    public Object visit(FuncCallIns ins);

    public Object visit(FuncDefIns ins);
    
    public Object visit(MoveIns ins);
    
    public Object visit(ReturnIns ins);
//    public Object visit(VarDefIns ins);
}

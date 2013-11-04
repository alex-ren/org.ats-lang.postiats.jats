package jats.utfpl.tree;

public interface TreeVisitor {
    public Object visit(ProgramTree node);
    public Object visit(ValBind node);
    public Object visit(ValDef node);
    public Object visit(VarDef node);
    public Object visit(VarArrayDef node);
    public Object visit(VarAssign node);
    public Object visit(FunGroup node);
    public Object visit(FunDef node);
    
    public Object visit(AppExp node);
    public Object visit(AtomExp node);
    public Object visit(IdExp node);
    public Object visit(IfExp node);
    public Object visit(LamExp node);
    public Object visit(LetExp node);
    public Object visit(TupleExp node);


}



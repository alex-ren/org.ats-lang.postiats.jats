package jats.utfpl.tree;

public interface TreeVisitor {
    public Object visit(Program node);
    public Object visit(ValDef node);
    public Object visit(FunDef node);
    
    public Object visit(AppExp node);
    public Object visit(AtomExp node);
    public Object visit(IdExp node);
    public Object visit(IfExp node);
    public Object visit(LamExp node);
    public Object visit(LetExp node);
    public Object visit(TupleExp node);


}



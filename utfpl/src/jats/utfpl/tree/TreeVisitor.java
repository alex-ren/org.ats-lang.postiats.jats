package jats.utfpl.tree;

public interface TreeVisitor {
    public Object visit(ProgramTree node);
    public Object visit(DecValBind node);
    public Object visit(DecValDef node);
    public Object visit(DecVarDef node);
    public Object visit(DecVarArrayDef node);
    public Object visit(DecVarAssign node);
    public Object visit(DecFunGroup node);
    public Object visit(DecFunDef node);
    public Object visit(DecExtCode node);
    
    public Object visit(ExpApp node);
    public Object visit(ExpAtom node);
    public Object visit(ExpId node);
    public Object visit(ExpIf node);
    public Object visit(ExpLam node);
    public Object visit(ExpLet node);
    public Object visit(ExpTuple node);
    public Object visit(DecFunDec decFunDec);
    public Object visit(DecFunImpl decFunImpl);
    public Object visit(DecMCGet decMCGet);
    public Object visit(PatRecord patRecord);
    public Object visit(PatAny patAny);
    public Object visit(PatEmpty patEmpty);
    public Object visit(PatIgnore patIgnore);
    public Object visit(PatVar patVar);


}



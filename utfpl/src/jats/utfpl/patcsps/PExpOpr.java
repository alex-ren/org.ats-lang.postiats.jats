package jats.utfpl.patcsps;

public class PExpOpr implements PExp {
    static enum Type {plus, minus, mul, div, gt, gte, lt, lte, eq, inc, dec};
    
    static public boolean isUnary(Type ty) {
        switch (ty) {
        case plus:
        case minus:
        case mul:
        case div:
        case gt:
        case gte:
        case lt:
        case lte:
        case eq:
            return false;
        default:
            return true;
        }
    }
    
    static public String stringType(Type ty) {
        switch (ty) {
        case plus:
            return "+";
        case minus:
            return "-";
        case mul:
            return "*";
        case div:
            return "/";
        case gt:
            return ">";
        case gte:
            return ">=";
        case lt:
            return "<";
        case lte:
            return "<=";
        case eq:
            return "==";
        case inc:
            return "++";
        case dec:
            return "--";
         default:
             throw new Error("Not supported");
        }
    }
    
    public PExp m_opr1;
    public PExp m_opr2;
    public Type m_type;
    
    public PExpOpr(Type ty, PExp opr1, PExp opr2) {
        m_type = ty;
        m_opr1 = opr1;
        m_opr2 = opr2;
    }
    
    

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}



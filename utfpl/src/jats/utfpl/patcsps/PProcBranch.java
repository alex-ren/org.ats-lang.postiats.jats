package jats.utfpl.patcsps;

public class PProcBranch implements PProc {
    public static enum Type {ifcommon, ifa, ifb}
    
    public PExp m_condExp;
    public PProc m_ifProc;
    public PProc m_elseProc;
    public Type m_ty;
    
    public PProcBranch(PExp condExp, PProc ifProc, PProc elseProc, Type ty) {
        m_condExp = condExp;
        m_ifProc = ifProc;
        m_elseProc = elseProc;
        m_ty = ty;
    }
    

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}

package jats.utfpl.instruction;


import java.util.List;

public class InsCond implements UtfplInstruction {
    public TID m_holder;
    public ValPrim m_cond;
    public List<UtfplInstruction> m_btrue;
    public List<UtfplInstruction> m_bfalse;  // cannot be null, but can be empty list
    private Boolean m_hasEffect;
    
    public InsCond(TID holder, ValPrim cond, 
            List<UtfplInstruction> btrue, List<UtfplInstruction> bfalse, 
            Boolean hasEffect) {
        m_holder = holder;
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
        m_hasEffect = hasEffect;
    }
    
    public void setEffectFlag(boolean flag) {
        m_hasEffect = flag;
    }

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return m_hasEffect;
    }

}

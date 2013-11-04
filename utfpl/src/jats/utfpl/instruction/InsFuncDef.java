package jats.utfpl.instruction;


import jats.utfpl.patcsps.type.PATTypeFunc;

//import java.util.ArrayList;
import java.util.List;

public class InsFuncDef implements UtfplInstruction {
    public TID m_name;
    public List<TID> m_paralst;
    public List<UtfplInstruction> m_body;
    public TID m_ret;
//    public List<TID> m_escParaLst;
    
    
    public InsFuncDef(TID name, List<TID> paralst, List<UtfplInstruction> body, TID ret) {
        m_name = name;
        m_paralst = paralst;
        m_body = body;
        m_ret = ret;
//        m_escParaLst = new ArrayList<TID>();
    }
    
    public void flagSideEffect() {
//        System.out.println("ddddddddddddd " + m_name);
//        System.out.println(m_name.getType());
        ((PATTypeFunc)m_name.getType()).updateEffect(true);
    }
    
    public boolean hasSideEffect() {
        return m_name.hasEffect();
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

}

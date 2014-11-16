package jats.utfpl.stfpl.mcinstruction;

//import jats.utfpl.stfpl.stype.Aux;

import java.util.List;

//import jats.utfpl.stfpl.csharptype.CSFunType;
//import jats.utfpl.stfpl.csharptype.CSVoidType;
//
//import org.stringtemplate.v4.ST;
//import org.stringtemplate.v4.STGroup;

public class MCInsCall implements IMCInstruction {
    public MCSId m_holder;
    public MCSId m_fun;
    public List<IMCValPrim> m_args;
//    public MCSId m_env;  // If holder is not a closure, then env is null.
    
    public MCInsCall(MCSId holder, MCSId fun, List<IMCValPrim> args) {
        m_holder = holder;
        m_fun = fun;
        m_args = args;
//        m_env = env;
        
        if (!m_fun.getSId().isFunName()) {
            throw new Error("This is not allowed. id is " + m_fun.getSId().toStringIns());
        }
    }

    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        // return Aux.hasEffect(m_fun.getType());
        return m_fun.hasEffect();
    }
    
    public boolean isTailCall() {
        return m_holder.getSId().isRetHolder();
    }
}
